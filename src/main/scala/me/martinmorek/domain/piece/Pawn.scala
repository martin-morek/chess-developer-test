package me.martinmorek.domain.piece

import me.martinmorek.domain.Color

/**
 * The pawn can move one or two squares forward on its first move (when not taking an opponent piece)
 * The pawn can move one square forward on subsequent moves (when not taking an opponent piece)
 * The pawn can move one square forward diagonally if taking an opponent piece
 */

class Pawn(color: Color) extends Chessman(color){
  override val restrictions = Set(PawnSpecific(color), OnlyForward(color))
}
