package me.martinmorek

import me.martinmorek.domain.{Black, Position, White}
import me.martinmorek.domain.piece._

object Chessboard {
  type Chessboard = Map[Position, Chessman]

  def startingState: Chessboard = Map(
    Position(1, 1) -> new Rook(White),
    Position(2, 1) -> new Knight(White),
    Position(3, 1) -> new Bishop(White),
    Position(4, 1) -> new Queen(White),
    Position(5, 1) -> new King(White),
    Position(6, 1) -> new Bishop(White),
    Position(7, 1) -> new Knight(White),
    Position(8, 1) -> new Rook(White),

    Position(1, 2) -> new Pawn(White),
    Position(2, 2) -> new Pawn(White),
    Position(3, 2) -> new Pawn(White),
    Position(4, 2) -> new Pawn(White),
    Position(5, 2) -> new Pawn(White),
    Position(6, 2) -> new Pawn(White),
    Position(7, 2) -> new Pawn(White),
    Position(8, 2) -> new Pawn(White),

    Position(1, 7) -> new Pawn(Black),
    Position(2, 7) -> new Pawn(Black),
    Position(3, 7) -> new Pawn(Black),
    Position(4, 7) -> new Pawn(Black),
    Position(5, 7) -> new Pawn(Black),
    Position(6, 7) -> new Pawn(Black),
    Position(7, 7) -> new Pawn(Black),
    Position(8, 7) -> new Pawn(Black),

    Position(1, 8) -> new Rook(Black),
    Position(2, 8) -> new Knight(Black),
    Position(3, 8) -> new Bishop(Black),
    Position(4, 8) -> new Queen(Black),
    Position(5, 8) -> new King(Black),
    Position(6, 8) -> new Bishop(Black),
    Position(7, 8) -> new Knight(Black),
    Position(8, 8) -> new Rook(Black)
  )
}
