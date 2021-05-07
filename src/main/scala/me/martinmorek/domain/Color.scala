package me.martinmorek.domain

sealed trait Color {
  def opponentColor: Color
}

case object White extends Color {
  override def opponentColor: Color = Black
}

case object Black extends Color {
  override def opponentColor: Color = White
}

