package me.martinmorek.domain.piece

import me.martinmorek.domain.Color

/**
 * The queen can move any number of squares horizontally, vertically or diagonally
 */

class Queen(color: Color) extends Chessman(color) {
  override val restrictions: Set[Restriction] = Set[Restriction](OnlyDiagonallyAndPerpendicularly)
}
