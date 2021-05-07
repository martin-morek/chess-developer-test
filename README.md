# Chess Programming Test

The program simply reads moves from a file and validates them. After each 
move execution renders new state of chessboard. It determines if a move leaves 
the player is in check.

Program is developed in Scala language version 2.13.5 

## How to run

To run a program from a command line a fat jar needs to be build. Solution 
contains test scenarios which can be run separately. 

### Running tests 
To run tests execute in terminal:

```sbt test```

### Building jar
To build a fat jar execute in terminal:

```sbt assembly```

### Running program
Program expects single argument which is the path to file with chess moves to validate.
The file should only contain a list of moves, each one on a new line, in a format: `abcd`.

where:
- `ab` represents chessman starting position, e.g. `c5`
- `cb` represent chessman new position, e.g. `d4`

Example input file:
```
e2e4
e7e5
b1c3
d7d6
h2h3
c8e6
h1h2
```

If, none parameters would be provided, program will process included example file.  

To run the jar execute in terminal:

```java -jar target/scala-2.13/chess-developer-test-assembly-0.1.jar <pathToInpunFile>```

#### Output example
```
Executing move: (e2 to e4)
    a   b   c   d   e   f   g   h  
  +---+---+---+---+---+---+---+---+
8 | r | n | b | q | k | b | n | r |
  +---+---+---+---+---+---+---+---+
7 | p | p | p | p | p | p | p | p |
  +---+---+---+---+---+---+---+---+
6 |   |   |   |   |   |   |   |   |
  +---+---+---+---+---+---+---+---+
5 |   |   |   |   |   |   |   |   |
  +---+---+---+---+---+---+---+---+
4 |   |   |   |   | P |   |   |   |
  +---+---+---+---+---+---+---+---+
3 |   |   |   |   |   |   |   |   |
  +---+---+---+---+---+---+---+---+
2 | P | P | P | P |   | P | P | P |
  +---+---+---+---+---+---+---+---+
1 | R | N | B | Q | K | B | N | R |
  +---+---+---+---+---+---+---+---+
================================================= 
```

## Game behavior
This solution isn't complete chess game. It serves only for validation moves.

### Requirements
1. The board starts in the standard chess starting state with all the 16 pieces lined up for each player.
2. Play starts with player 1 (white) and on each valid move alternates to the other
   player. On an invalid move game ends with explanation why move is invalid.
3. The moves are read from provided input file until there are no more moves
4. All moves must have a piece on the starting square and either an opponent piece or nothing on the destination square Anything else is invalid.
5. Validation rules:
   - The king can move only 1 square but in any direction
   - The bishop can move any number of squares but only diagonally
   - The rook can move any number of squares but only horizontally or
   vertically
   - The queen can move any number of squares horizontally, vertically or
   diagonally.
   - The knight can move in an L shape with sides of 2 and 1 squares
   respectively. That is 8 different possible moves. Unlike other pieces it
   jumps over other pieces.
   - The pawn can move one or two squares forward on its first move (when
   not taking an opponent piece)
   - The pawn can move one square forward on subsequent moves (when not
   taking an opponent piece)
   - The pawn can move one square forward diagonally if taking an opponent
   piece
6. After each successful move the board rendered in simple ASCII form. Player 1 is represented by upper-case characters and player 2 by lower-case characters.
7. If the destination square contains an opponent piece then that piece is removed from the board.
8. For pieces other than the knight disallow the move if there are any other pieces in the way between the start and end square.
9. If a move ends with a player’s king under attack that is “check”
10. A player cannot end their own move in check
11. If a player starts their move in check this should be displayed as “in check”