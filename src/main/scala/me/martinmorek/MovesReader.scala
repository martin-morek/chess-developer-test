package me.martinmorek

import me.martinmorek.domain.Move

import scala.util.{Failure, Success, Try}

object MovesReader {
  def readMovesFromFile(filePath: String): List[Move] = {
    val inputFile = new UserInputFile(filePath)

    def read(file: UserInputFile, acc: List[Move]): List[Move] = {
      Try(Move.fromIntArray(file.nextMove())) match {
        case Failure(_ : NullPointerException) => acc
        case Success(move) => read(file, acc :+ move)
      }
    }
    read(inputFile, List())
  }
}
