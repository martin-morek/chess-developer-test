package me.martinmorek

import me.martinmorek.Chessboard.Chessboard
import me.martinmorek.domain.{Color, Move, Position, White}
import me.martinmorek.domain.piece.{Chessman, King, Knight}

case class GameState(chessboard: Chessboard, onTurn: Color)

object GameState{
  def initialize: GameState = new GameState(Chessboard.startingState, White)
}

object Game {

  def play(gameState: GameState, moves: List[Move], acc: List[Either[String, GameState]]): List[Either[String, GameState]] =
    moves match {
      case move :: tail =>
        Game.executeMove(move, gameState) match {
          case Right(newState) =>
            play(newState, tail, acc :+ Right(newState))
          case Left(error) =>
            acc :+ Left(error)
        }
      case _ => acc
    }

  def executeMove(move: Move, state: GameState): Either[String, GameState] = {
    for {
      chessman <- state.chessboard.get(move.currentPosition)
        .fold(Left("None chessman on starting position!"): Either[String, Chessman])(ch => Right(ch))
      _ <- Either.cond(chessman.color == state.onTurn, (), s"${state.onTurn} are on turn!" )
      _ <- validateMove(move, chessman, state)
      chessboard <- Right(updateChessboard(move, chessman, state.chessboard))
      _ <- Either.cond(!inCheck(GameState(chessboard, state.onTurn)), (), "Cannot end move in check!")
    } yield GameState(chessboard, state.onTurn.opponentColor)
  }

  def validateMove(move: Move, chessman: Chessman, state: GameState): Either[String, Boolean] = {
    for {
      takingOpp <- isTakingOpponent(move, chessman, state.chessboard)
      allowedMove <- Either.cond(chessman.isMoveAllowed(move, takingOpp), move, s"Not allowed move for $chessman!")
      valid <- Either.cond(chessman.isInstanceOf[Knight] || isPathClear(allowedMove, state.chessboard),
        true, "Path is not clear!")
    } yield valid
  }

  def inCheck(gameState: GameState): Boolean = {
    val (kingPosition, _) = gameState.chessboard.find(value =>
      value._2.isInstanceOf[King] && value._2.color == gameState.onTurn).getOrElse(throw new RuntimeException("King is missing!"))
    // Exception has to be thrown because King is missing on the board and game doesn't make sense any more,
    // but this should never happen!

    val pieces = gameState.chessboard.filter({case (_, chessman) =>
      chessman.color == gameState.onTurn.opponentColor
    })

    pieces.map { case(position, chessman) =>
      val toKingMove = Move(position, kingPosition)
      validateMove(toKingMove, chessman, gameState) match {
        case Left(_) => false
        case Right(value) => value
      }
    }.foldLeft(false)(_ || _)
  }

  def updateChessboard(move: Move, chessman: Chessman, chessboard: Chessboard): Chessboard = {
    chessboard + (move.newPosition -> chessman) - move.currentPosition
  }

  def isTakingOpponent(move: Move, chessman: Chessman, chessboard: Chessboard): Either[String, Boolean] = {
    val destination = chessboard.get(move.newPosition)

    destination match {
      case Some(occupant)
        if occupant.color == chessman.color => Left("One of your chessmen is already on the destination tile!")
      case Some(_) => Right(true) // opponents chessman there, removing it
      case _ => Right(false) // empty tile
    }
  }

  def isPathClear(move: Move, chessboard: Chessboard): Boolean = {
    val up = move.isMovingHigherRow
    val down = move.isMovingToLowerRow
    val right = move.isMovingRight
    val left = move.isMovingLeft

    val nextTile = (up, down, right, left) match {
      case (true, false, false, false) => // moving up
        (step: Int) => Position(move.currentPosition.column, move.currentPosition.row + step)
      case (false, true, false, false) => // moving down
        (step: Int) => Position(move.currentPosition.column, move.currentPosition.row - step)
      case (false, false, true, false) => // moving right
        (step: Int) => Position(move.currentPosition.column + step, move.currentPosition.row)
      case (false, false, false, true) => // moving left
        (step: Int) => Position(move.currentPosition.column - step, move.currentPosition.row)
      case (true, false, true, false) =>  // moving up and right
        (step: Int) => Position(move.currentPosition.column + step, move.currentPosition.row + step)
      case (true, false, false, true) =>  // moving up and left
        (step: Int) => Position(move.currentPosition.column - step, move.currentPosition.row + step)
      case (false, true, true, false) =>  // moving down and right
        (step: Int) => Position(move.currentPosition.column + step, move.currentPosition.row - step)
      case (false, true, false, true) =>  // moving down and left
        (step: Int) => Position(move.currentPosition.column - step, move.currentPosition.row - step)
    }

    (1 until move.totalLength).foldLeft(true) { case(result, step) =>
      chessboard.get(nextTile(step)) match {
        case Some(_) => false
        case None => result
      }
    }
  }
}
