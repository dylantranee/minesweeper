# Minesweeper Game

## Author

**Name:** Trần Anh Văn  
**Student ID:** ITITSB22017

## Overview

This project is a graphical implementation of the classic Minesweeper game. The game initializes a grid where mines are
randomly placed, and the player interacts with the grid to reveal tiles or flag potential mines. The game logic and user
interactions are handled using the Command design pattern and implemented using Java Swing for the graphical user
interface.

## Usage

To run the Minesweeper game:
1. Ensure you have Java installed on your system.
2. Compile all the Java files in the project.
3. Run the "**App**" class to start the game.

## Project Structure

The project consists of several Java classes, each with a specific responsibility:

### Main Class
* **App:** Initializes the Minesweeper game by obtaining the Singleton instance of the Minesweeper class.

### Command Interface
* **Command:** An interface that defines a common method execute for different commands in the Minesweeper game.
Implementing classes (FlagTileCommand and RevealTileCommand) provide specific behaviors for different types of user 
interactions.

### Command Implementations
* **FlagTileCommand:** Implements the Command interface to provide the functionality for flagging and unflagging a mine
tile. When executed, it toggles a flag on the specified tile.


* **RevealTileCommand:** Implements the Command interface to provide the functionality for revealing a mine tile. When
executed, it checks if the tile contains a mine and either reveals the mine or recursively reveals adjacent tiles if no
mines are nearby.

### Main Game Logic
* **Minesweeper:** The main class that handles the Minesweeper game logic. It sets up the game board, manages the
placement of mines, and handles user interactions such as revealing tiles and flagging potential mines. This class is
implemented as a Singleton to ensure only one instance of the game exists.

## Dependencies
* Java Development Kit (JDK)
* Java Swing (for GUI components)

## How to Contribute

1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Make your changes.
4. Submit a pull request with a detailed description of your changes.
