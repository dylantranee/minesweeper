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
