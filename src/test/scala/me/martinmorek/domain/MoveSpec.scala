package me.martinmorek.domain

import org.scalatest.wordspec.AnyWordSpec

class MoveSpec extends AnyWordSpec {
    "fromIntArray" should {
      "transform file input 'e2e4' to correct game Move" in {
        val ints: Array[Int] = Array[Int](4, 6, 4, 4)

        val move = Move.fromIntArray(ints)
        assert(move.currentPosition == Position(5,2))
        assert(move.newPosition == Position(5,4))
      }

      "evaluate file input 'e2e9' as incorrect chess move" in {
        val ints: Array[Int] = Array[Int](4, 6, 4, -1)
        assertThrows[IllegalArgumentException](Move.fromIntArray(ints))
      }
    }
}
