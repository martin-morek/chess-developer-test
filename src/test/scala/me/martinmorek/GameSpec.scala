package me.martinmorek

import me.martinmorek.domain.{Black, Move, Position, White}
import me.martinmorek.domain.piece.{King, Pawn, Queen}
import org.scalatest.wordspec.AnyWordSpec

class GameSpec extends AnyWordSpec {
  val gameState = GameState.initialize


  "Execute move" should {
    "fail if move starting in position without chessman" in {
      val move = Move(Position(1,4), Position(1,5))
      val result = Game.executeMove(move, gameState)
      assert(result == Left("None chessman on starting position!"))
    }

    "fail if wrong player is moving" in {
      val move = Move(Position(1,7), Position(1,6))
      val result = Game.executeMove(move, gameState)
      assert(result == Left("White are on turn!"))
    }

    "fail if new position is already occupied by current player" in {
      val move = Move(Position(1,1), Position(1,2))
      val result = Game.executeMove(move, gameState)
      assert(result == Left("One of your chessmen is already on the destination tile!"))
    }

    "fail if not allowed for the chessman" in {
      val move = Move(Position(1,2), Position(2,3))
      val result = Game.executeMove(move, gameState)
      assert(result == Left("Not allowed move for Pawn!"))
    }

    "fail if path is not clear" in {
      val move = Move(Position(1,1), Position(1,5))
      val result = Game.executeMove(move, gameState)
      assert(result == Left("Path is not clear!"))
    }

    "fail if player ends move in check" in {
      val chessboard = Map(
        Position(8,4) -> new Queen(Black),
        Position(5,1) -> new King(White),
        Position(1,2) -> new Pawn(White)
      )

      val notMovingKingToSafeMove = Move(Position(1,2), Position(1, 3))
      val result = Game.executeMove(notMovingKingToSafeMove, GameState(chessboard, White))
      assert(result == Left("Cannot end move in check!"))
    }
  }

}
