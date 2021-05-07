package me.martinmorek.domain.piece

import me.martinmorek.domain.{Color, White}

/**
 * The knight can move in an L shape with sides of 2 and 1 squares
 * respectively. That is 8 different possible moves. Unlike other pieces it
 * jumps over other pieces.
 */

class Knight(color: Color) extends Chessman(color) {
  override val restrictions: Set[Restriction] = Set(OnlyLShape)

  override def shortName: Char = {
    val firstChar = this.getClass.getSimpleName.charAt(1)

    if(color == White) firstChar.toUpper
    else firstChar.toLower
  }
}
