import me.martinmorek.Game.play
import me.martinmorek.MovesReader.readMovesFromFile
import me.martinmorek._
import me.martinmorek.domain.Move

import scala.util.Try

object Chess {

  def renderMoves(moves: List[Move]): Unit = {
    (play(GameState.initialize, moves, List()) zip moves).foreach {
      case (Left(error), move) =>
        println(s"Move $move cannot be executed because:\n - $error\n")
        System.exit(1)
      case (Right(state), move) =>
        println(s"Executing move: $move")
        if (Game.inCheck(state)) println("Check!")
        Renderer.renderChessboard(state.chessboard)
        if (Game.inCheck(state)) println("In check!")
    }
  }

  def main(args: Array[String]): Unit = {
    val filePath = Try(args(0)).toOption.getOrElse("data/checkmate.txt")
    val moves = readMovesFromFile(filePath)

    Renderer.renderChessboard(Chessboard.startingState)
    renderMoves(moves)
  }
}
