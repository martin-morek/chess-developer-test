package me.martinmorek.integration

import me.martinmorek.{Game, GameState, MovesReader}
import org.scalatest.wordspec.AnyWordSpec

class IntegrationSpec extends AnyWordSpec {

  "Chess game" should {
    "evaluate if check" in {
      val moves = MovesReader.readMovesFromFile("src/test/resources/checkmate.txt")
      val gameStates: List[Either[String, GameState]] = Game.play(GameState.initialize, moves, List())

      assert(gameStates.size == 6)
      assert(!gameStates.exists(_.isLeft))
      gameStates.last match {
        case Right(state) => assert(Game.inCheck(state))
      }
    }

    "ends if all moves were applied successfully" in {
      val moves = MovesReader.readMovesFromFile("src/test/resources/sample-moves.txt")
      val gameStates: List[Either[String, GameState]] = Game.play(GameState.initialize, moves, List())

      assert(gameStates.size == 7)
      assert(!gameStates.exists(_.isLeft))
    }

    "ends prematurely if one of the moves is invalid" in {
      val moves = MovesReader.readMovesFromFile("src/test/resources/sample-moves-invalid.txt")
      val gameStates: List[Either[String, GameState]] = Game.play(GameState.initialize, moves, List())

      assert(gameStates.size == 3)
      assert(gameStates.last.isLeft)
    }
  }
}
