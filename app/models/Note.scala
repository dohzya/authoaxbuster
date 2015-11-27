package models

import scala.math.Ordering

case class Note(
  votesPro: Int = 0,
  votesCons: Int = 0
) {
  def value = votesPro - votesCons
}
object Note {
  implicit def ordering: Ordering[Note] = {
    Ordering.by(- _.value)
  }
}
