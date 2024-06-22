/* Name: Trần Anh Văn
   Student ID: ITITSB22017
   Purpose:  Implements the Command interface to provide the functionality for flagging and unflagging a mine tile.
             When executed, it toggles a flag on the specified tile.
*/

public class FlagTileCommand implements Command {
    private final Minesweeper.MineTile tile;

    public FlagTileCommand(Minesweeper.MineTile tile) {
        this.tile = tile;
    }

    @Override
    public void execute() {
        if (tile.getText().isEmpty() && tile.isEnabled()) {
            tile.setText("\uD83D\uDEA9");
        } else if (tile.getText().equals("\uD83D\uDEA9")) {
            tile.setText("");
        }
    }
}
