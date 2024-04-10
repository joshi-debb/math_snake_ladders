# Math Snake Ladders

This Java application is a combination of the classic Snake and Ladders game with math-based penalties. Players move across a game board, encountering mathematical problems when landing on specific squares.

## Features

- **Game Board**: The game board consists of an 8x8 grid representing the game environment.
- **Player Movement**: Players roll a die to determine their movement across the board.
- **Penalties**: Certain squares on the board trigger mathematical penalties of varying difficulties: easy, medium, and hard.
- **Math Problems**: Players must solve math problems corresponding to the difficulty level of the penalty square they land on.
- **Matrix Operations**: The penalties involve matrix operations such as addition and division.
- **Win Condition**: The game ends when a player reaches the final square on the board.

## How to Play

1. **Menu**: Upon starting the game, players are presented with a menu where they can choose to start a new game, resume a game, or exit.
  
![image](https://github.com/joshi-debb/math_snake_ladders/assets/87725718/ee4a7584-d8a4-4262-8ccf-e2ef5001c97f)

4. **Game Board**: The game board is displayed with the player's current position represented by '@' and penalty squares represented by '#'.
5. **Movement**: Players roll a die to determine their movement across the board. They can choose to roll the die or return to the main menu.
6. **Penalties**: Landing on certain squares triggers mathematical penalties. The player must solve these penalties to continue.
7. **Winning**: The game ends when a player reaches the final square on the board.

## Usage

1. **Starting the Game**: Run the `main` method in the `MathSnakeLadders` class.
2. **Menu Navigation**: Use the numeric keys to navigate the main menu options.
3. **Rolling the Die**: Press '1' to roll the die and move the player.
4. **Solving Penalties**: Follow the on-screen instructions to solve mathematical penalties.

## Notes

- The game supports resuming from a saved state.
- Penalties become more difficult as players progress through the game board.

## Dependencies

- This application requires Java SE Development Kit (JDK) 8 to compile and run.
