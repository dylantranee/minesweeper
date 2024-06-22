/* Name: Trần Anh Văn
   Student ID: ITITSB22017
   Purpose: Implements the Command interface to provide the functionality for revealing a mine tile. When executed, it
            checks if the tile contains a mine and either reveals the mine or recursively reveals adjacent tiles if no
            mines are nearby.
*/

public class RevealTileCommand implements Command {
    private final Minesweeper game;
    private final Minesweeper.MineTile tile;

    public RevealTileCommand(Minesweeper game, Minesweeper.MineTile tile) {
        this.game = game;
        this.tile = tile;
    }

    @Override
    public void execute() {
        game.revealTile(tile);
    }
}
