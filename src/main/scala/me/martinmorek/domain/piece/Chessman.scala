package me.martinmorek.domain.piece

import me.martinmorek.domain.{Color, Move, White}

abstract class Chessman(val color: Color) {
  val restrictions: Set[Restriction]

  def isMoveAllowed(move: Move, takingOpponent: Boolean): Boolean = restrictions.forall(_.complains(move, takingOpponent))

  def shortName: Char = {
    val firstChar = this.getClass.getSimpleName.charAt(0)

    if(color == White) firstChar.toUpper
    else firstChar.toLower
  }

  override def toString: String = this.getClass.getSimpleName
}


sealed trait Restriction {
  def complains(move: Move, takingOpponent: Boolean): Boolean
}

case object OnlyOneSquare extends Restriction {
  override def complains(move: Move, takingOpponent: Boolean): Boolean = move.totalLength == 1
}

case class PawnSpecific(color: Color) extends Restriction {
  override def complains(move: Move, takingOpponent: Boolean): Boolean = {
    if(takingOpponent) {
      OnlyOneSquare.complains(move, takingOpponent) && OnlyDiagonally.complains(move, takingOpponent)
    } else {
      if(move.totalLength == 1)
        OnlyOneSquare.complains(move, takingOpponent) && move.isMovingOnlyVertically
      else {
        if(color == White)
          move.currentPosition.row == 2 && move.totalLength == 2
        else
          move.currentPosition.row == 7 && move.totalLength == 2
      }
    }
  }
}

case object OnlyDiagonally extends Restriction {
  override def complains(move: Move, takingOpponent: Boolean): Boolean = move.isMovingDiagonally
}

case object OnlyPerpendicularly extends Restriction {
  override def complains(move: Move, takingOpponent: Boolean): Boolean =
    move.isMovingOnlyVertically || move.isMovingOnlyHorizontally
}

case object OnlyDiagonallyAndPerpendicularly extends Restriction {
  override def complains(move: Move, takingOpponent: Boolean): Boolean =
    OnlyDiagonally.complains(move, takingOpponent) || OnlyPerpendicularly.complains(move, takingOpponent)
}

case class OnlyForward(color: Color) extends Restriction {
  override def complains(move: Move, takingOpponent: Boolean): Boolean = {
    if(color == White) move.isMovingHigherRow
    else move.isMovingToLowerRow
  }
}

case object OnlyLShape extends Restriction {
  override def complains(move: Move, takingOpponent: Boolean): Boolean = {
    (move.rowMoveLength == 2 || move.columnMoveLength == 2) && (move.rowMoveLength == 1 || move.columnMoveLength == 1)
  }
}