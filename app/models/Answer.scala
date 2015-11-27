package models

case class Answer(
  content: String = "",
  note: Note = Note(),
  comments: Seq[Comment] = Nil
)
