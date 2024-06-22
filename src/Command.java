/* Name: Trần Anh Văn
   Student ID: ITITSB22017
   Purpose: An interface that defines a common method execute for different commands in the Minesweeper game.
            Implementing classes (FlagTileCommand and RevealTileCommand) will provide specific behaviors for different
            types of user interactions.
*/

public interface Command {
    void execute();
}