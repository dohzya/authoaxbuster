package models

import java.time.Clock
import java.time.ZonedDateTime

case class Topic(
  id: String = new String(scala.util.Random.alphanumeric.take(10).toArray),
  title: String,
  desc: String,
  urls: Seq[String] = Nil,
  comments: Seq[Comment] = Nil,
  answers: Seq[Answer] = Nil,
  nViews: Int = 0,
  creationDate: ZonedDateTime = ZonedDateTime.now(Clock.systemUTC)
) {
  def nAnswers = answers.size
  def confidence = {
    answers.map(a => a.impact * 10).foldLeft(50.0){_ + _}
  }
}
