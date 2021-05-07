package me.martinmorek.domain.piece

import me.martinmorek.domain.Color

/**
 * The rook can move any number of squares but only horizontally or vertically
 */

class Rook(color: Color) extends Chessman(color) {
  override val restrictions: Set[Restriction] = Set(OnlyPerpendicularly)
}
