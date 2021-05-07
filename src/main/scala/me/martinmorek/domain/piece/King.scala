package me.martinmorek.domain.piece

import me.martinmorek.domain.Color

/**
 * The king can move only 1 square but in any direction
 */

class King (color: Color) extends Chessman(color) {
  override val restrictions: Set[Restriction] = Set(OnlyOneSquare)
}
