package me.martinmorek.domain.piece

import me.martinmorek.domain.{Move, Position, White}
import org.scalatest.wordspec.AnyWordSpec

class ChessmanSpec extends AnyWordSpec {
  val diagonalMove = Move(Position(3,4), Position(5,2))
  val diagonalSingleMove = Move(Position(1,2), Position(2,3))
  val verticalMove = Move(Position(1,1),Position(1,6))
  val verticalSingleMove = Move(Position(1,1),Position(1,2))
  val verticalBackwardMove = Move(Position(1, 4), Position(1, 3))
  val horizontalMove = Move(Position(1,4), Position(6,4))
  val horizontalSingleMove = Move(Position(1,4), Position(2,4))
  val lShapeMove = Move(Position(1,1), Position(2,3))


  "Bishop" should {
    val bishop = new Bishop(White)

    "can move diagonally" in {
      assert(bishop.isMoveAllowed(diagonalSingleMove, false))
      assert(bishop.isMoveAllowed(diagonalMove, false))
    }

    "cannot move vertically" in {
      assert(!bishop.isMoveAllowed(verticalSingleMove, false))
      assert(!bishop.isMoveAllowed(verticalMove, false))
    }

    "cannot move horizontally" in {
      assert(!bishop.isMoveAllowed(horizontalSingleMove, false))
      assert(!bishop.isMoveAllowed(horizontalMove, false))
    }

    "cannot move in LShape" in {
      assert(!bishop.isMoveAllowed(lShapeMove, false))
    }
  }

  "King" should {
    val king = new King(White)

    "can move diagonally one step" in {
      assert(king.isMoveAllowed(diagonalSingleMove, false))
    }

    "can move vertically one step" in {
      assert(king.isMoveAllowed(verticalSingleMove, false))
      assert(king.isMoveAllowed(verticalBackwardMove, false))
    }

    "can move horizontally one step" in {
      assert(king.isMoveAllowed(horizontalSingleMove, false))
    }

    "cannot move diagonally multiple steps" in {
      assert(!king.isMoveAllowed(diagonalMove, false))
    }

    "cannot move vertically multiple steps" in {
      assert(!king.isMoveAllowed(verticalMove, false))
    }

    "cannot move horizontally multiple steps" in {
      assert(!king.isMoveAllowed(horizontalMove, false))
    }

    "cannot move in LShape" in {
      assert(!king.isMoveAllowed(lShapeMove, false))
    }
  }

  "Knight" should {
    val knight = new Knight(White)

    "can move in LShape" in {
      assert(knight.isMoveAllowed(lShapeMove, false))
    }

    "cannot move diagonally" in {
      assert(!knight.isMoveAllowed(diagonalSingleMove, false))
      assert(!knight.isMoveAllowed(diagonalMove, false))
    }

    "cannot move vertically" in {
      assert(!knight.isMoveAllowed(verticalSingleMove, false))
      assert(!knight.isMoveAllowed(verticalMove, false))
    }

    "cannot move horizontally" in {
      assert(!knight.isMoveAllowed(horizontalSingleMove, false))
      assert(!knight.isMoveAllowed(horizontalMove, false))
    }
  }

  "Pawn" should {
    val pawn = new Pawn(White)

    "can move diagonally one step if taking opponent" in {
      assert(pawn.isMoveAllowed(diagonalSingleMove, true))
    }

    "cannot move diagonally if not taking opponent" in {
      assert(!pawn.isMoveAllowed(diagonalSingleMove, false))
    }

    "can move vertically two steps if it is first move" in {
      val verticalTwoSteps = Move(Position(1,2),Position(1,4))
      assert(pawn.isMoveAllowed(verticalTwoSteps, false))
    }

    "can move vertically only step if not ist first move" in {
      assert(!pawn.isMoveAllowed(verticalMove, false))
    }

    "cannot move vertically multiple steps" in {
      assert(!pawn.isMoveAllowed(verticalMove, false))
    }

    "cannot move diagonally multiple steps" in {
      assert(!pawn.isMoveAllowed(diagonalMove, false))
    }

    "cannot move horizontally" in {
      assert(!pawn.isMoveAllowed(horizontalMove, false))
    }

    "cannot move in LShape" in {
      assert(!pawn.isMoveAllowed(lShapeMove, false))
    }

    "cannot move backward" in {
      assert(!pawn.isMoveAllowed(verticalBackwardMove, false))
    }
  }

  "Queen" should {
    val queen = new Queen(White)

    "can move diagonally steps" in {
      assert(queen.isMoveAllowed(diagonalSingleMove, false))
      assert(queen.isMoveAllowed(diagonalMove, false))
    }

    "can move vertically steps" in {
      assert(queen.isMoveAllowed(verticalSingleMove, false))
      assert(queen.isMoveAllowed(verticalMove, false))
      assert(queen.isMoveAllowed(verticalBackwardMove, false))

    }

    "can move horizontally steps" in {
      assert(queen.isMoveAllowed(horizontalSingleMove, false))
      assert(queen.isMoveAllowed(horizontalMove, false))
    }

    "cannot move in LShape" in {
      assert(!queen.isMoveAllowed(lShapeMove, false))
    }
  }

  "Rook" should {
    val rook = new Rook(White)

    "can move vertically steps" in {
      assert(rook.isMoveAllowed(verticalSingleMove, false))
      assert(rook.isMoveAllowed(verticalMove, false))
      assert(rook.isMoveAllowed(verticalBackwardMove, false))
    }

    "can move horizontally steps" in {
      assert(rook.isMoveAllowed(horizontalSingleMove, false))
      assert(rook.isMoveAllowed(horizontalMove, false))
    }

    "cannot move diagonally" in {
      assert(!rook.isMoveAllowed(diagonalSingleMove, false))
      assert(!rook.isMoveAllowed(diagonalMove, false))
    }

    "cannot move in LShape" in {
      assert(!rook.isMoveAllowed(lShapeMove, false))
    }
  }
}
