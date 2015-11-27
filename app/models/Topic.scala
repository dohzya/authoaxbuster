package models

import java.time.Clock
import java.time.ZonedDateTime

case class Topic(
  id: String = new String(scala.util.Random.alphanumeric.take(10).toArray),
  title: String,
  desc: String,
  note: Note = Note(),
  comments: Seq[Comment] = Nil,
  answers: Seq[Answer] = Nil,
  nViews: Int = 0,
  creationDate: ZonedDateTime = ZonedDateTime.now(Clock.systemUTC)
) {
  def nAnswers = answers.size
}
