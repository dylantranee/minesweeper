/* Name: Trần Anh Văn
   Student ID: ITITSB22017
   Purpose: The main class that handles the Minesweeper game logic. It sets up the game board, manages the placement of
            mines, and handles user interactions such as revealing tiles and flagging potential mines. This class is
            implemented as a Singleton to ensure only one instance of the game exists.
*/

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public final class Minesweeper {
    private static final Minesweeper minesweeper = new Minesweeper();

    public static class MineTile extends JButton {
        int r;
        int c;

        public MineTile(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    int tileSize = 48;
    int numRows = 9;
    int numCols = numRows;
    int boardWidth = numCols * tileSize;
    int boardHeight = numRows * tileSize;

    JFrame frame = new JFrame("Minesweeper");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    int mineCount = 10;
    MineTile[][] board = new MineTile[numRows][numCols];
    ArrayList<MineTile> mineList;
    Random random = new Random();

    int tilesClicked = 0;
    boolean gameOver = false;

    private Minesweeper() {
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setFont(new Font("Open Sans", Font.BOLD, 15));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Mines: " + mineCount);
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(numRows, numCols)); // 9x9
        frame.add(boardPanel);

        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                MineTile tile = new MineTile(r, c);
                board[r][c] = tile;

                tile.setFocusable(false);
                tile.setMargin(new Insets(0, 0, 0, 0));
                tile.setFont(new Font("Open Sans Unicode MS", Font.PLAIN, 24));
                tile.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (gameOver) {
                            return;
                        }
                        MineTile tile = (MineTile) e.getSource();
                        Command command;

                        // Left click
                        if (e.getButton() == MouseEvent.BUTTON1) {
                           command = new RevealTileCommand(minesweeper, tile);
                        }
                        // Right click
                        else if (e.getButton() == MouseEvent.BUTTON3) {
                           command = new FlagTileCommand(tile);
                        } else {
                            return;
                        }
                        command.execute();
                    }
                });
                boardPanel.add(tile);
            }
        }

        frame.setVisible(true);
        setMines();
    }

    public static Minesweeper getInstance() {
        return minesweeper;
    }

    void setMines() {
        mineList = new ArrayList<>();

        int mineLeft = mineCount;
        while (mineLeft > 0) {
            int r = random.nextInt(numRows); // 0-8
            int c = random.nextInt(numCols); // 0-8

            MineTile tile = board[r][c];
            if (!mineList.contains(tile)) {
                mineList.add(tile);
                mineLeft -= 1;
            }
        }
    }

    void revealMines() {
        for (MineTile tile : mineList) {
            tile.setText("\uD83D\uDCA3"); // Bomb emoji
        }

        gameOver = true;
        textLabel.setText("Game Over!");
    }

    void checkMine(int r, int c) {
        // Base case: When the tile is out of bounds
        if (r < 0 || r >= numRows || c < 0 || c >= numCols) {
            return;
        }

        MineTile tile = board[r][c];
        // Base case: When the tile has already been clicked on
        if (!tile.isEnabled()) {
            return;
        }
        tile.setEnabled(false);
        tilesClicked += 1;

        int minesFound = getMinesFound(r, c);

        if (minesFound > 0) {
            tile.setText(Integer.toString(minesFound));
        }
        else {
            tile.setText("");

            // Recursively check 8 surrounding tiles
            // Check 3 top tiles
            checkMine(r-1, c-1); // Top left
            checkMine(r-1, c);      // Top
            checkMine(r-1, c+1); // Top right

            // Check left and right tiles
            checkMine(r, c-1);  // Left
            checkMine(r, c+1);  // Right

            // Check 3 bottom tiles
            checkMine(r+1, c-1); // Bottom left
            checkMine(r+1, c);      // Bottom
            checkMine(r+1, c+1); // Bottom right
        }

        if (tilesClicked == numRows * numCols - mineList.size()) {
            gameOver = true;
            textLabel.setText("Congratulations!");
        }
    }

    private int getMinesFound(int r, int c) {
        int minesFound = 0;

        // Check 3 top tiles
        minesFound += countMine(r -1, c -1); // Top left
        minesFound += countMine(r -1, c);      // Top
        minesFound += countMine(r -1, c +1); // Top right

        // Check left and right tiles
        minesFound += countMine(r, c -1); // Left
        minesFound += countMine(r, c +1); // Right

        // Check 3 bottom tiles
        minesFound += countMine(r +1, c -1); // Bottom left
        minesFound += countMine(r +1, c);      // Bottom
        minesFound += countMine(r +1, c +1); // Bottom right
        return minesFound;
    }

    void revealTile(MineTile tile) {
        if (tile.getText().isEmpty()) {
            if (mineList.contains(tile)) {
                revealMines();
            } else {
                checkMine(tile.r, tile.c);
            }
        }
    }

    int countMine(int r, int c) {
        if (r < 0 || r >= numRows || c < 0 || c >= numCols) {
            return 0;
        }
        if (mineList.contains(board[r][c])) {
            return 1;
        }
        return 0;
    }
}
