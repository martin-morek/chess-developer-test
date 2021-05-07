package me.martinmorek.domain.piece

import me.martinmorek.domain.Color

/**
 * The bishop can move any number of squares but only diagonally
 */

class Bishop(color: Color) extends Chessman(color) {
  override val restrictions: Set[Restriction] = Set(OnlyDiagonally)
}
