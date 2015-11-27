package models

import scala.math.Ordering

case class Votes(
  pro: Int = 0,
  cons: Int = 0
) {
  def confidence: Double = (50 + pro - cons) / 100.0
}
