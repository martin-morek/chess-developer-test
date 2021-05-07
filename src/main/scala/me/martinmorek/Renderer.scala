package me.martinmorek

import me.martinmorek.Chessboard.Chessboard
import me.martinmorek.domain.Position

object Renderer {

  def printLineSeparator = println("   +---+---+---+---+---+---+---+---+")

  def renderChessboard(chessboard: Chessboard) = {
    print("     a   b   c   d   e   f   g   h  \n")
    printLineSeparator

    for (row <- (1 to 8).reverse) {
      print(s" $row |")
      for(column <- 1 to 8) {
        val position = Position(column, row)

        chessboard.get(position) match {
          case Some(chessman) => print(s" ${chessman.shortName} |")
          case None => print("   |")
        }
      }

      println("")
      printLineSeparator
    }
    println("=================================================\n")
  }
}
