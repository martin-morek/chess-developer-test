package me.martinmorek.domain

case class Move(currentPosition: Position, newPosition: Position) {
  override def toString: String =
    s"""(${(currentPosition.column + 96).toChar}${currentPosition.row} to ${(newPosition.column + 96).toChar}${newPosition.row})""".stripMargin

  val columnMoveLength: Int = math.abs(currentPosition.column - newPosition.column)
  val rowMoveLength: Int = math.abs(currentPosition.row - newPosition.row)
  val totalLength: Int = math.max(rowMoveLength,columnMoveLength)

  val isStayingInColumn: Boolean = currentPosition.column == newPosition.column
  val isStayingInRow: Boolean = currentPosition.row == newPosition.row

  val isMovingHigherRow: Boolean = currentPosition.row < newPosition.row
  val isMovingToLowerRow: Boolean = currentPosition.row > newPosition.row
  val isMovingOnlyVertically: Boolean = (isMovingHigherRow || isMovingToLowerRow) && isStayingInColumn

  val isMovingLeft: Boolean = currentPosition.column > newPosition.column
  val isMovingRight: Boolean = currentPosition.column < newPosition.column
  val isMovingOnlyHorizontally: Boolean = (isMovingRight || isMovingLeft) && isStayingInRow

  val isMovingDiagonally: Boolean = columnMoveLength == rowMoveLength
}

object Move {
  private def fromDigit(i: Int): Int = (56 - i).toChar.asDigit
  private def fromChar(i: Int): Int = (49 + i).toChar.asDigit

  def fromIntArray(ints: Array[Int]): Move = {
    val column1 = fromChar(ints(0))
    val row1 = fromDigit(ints(1))
    val currentPosition = Position(column1, row1)

    val column2 = fromChar(ints(2))
    val row2 = fromDigit(ints(3))
    val newPosition = Position(column2, row2)

    if(column1 > 8 || column1 < 1 || column2 > 8 || column2 < 1 || row1 > 8 || row1 < 1 || row2 > 8 || row2 < 1)
      throw new IllegalArgumentException("Move is out of chessboard")

    Move(currentPosition, newPosition)
  }
}
