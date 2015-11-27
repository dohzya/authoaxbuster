package models

case class Answer(
  value: Int = -1,
  content: String = "",
  votes: Votes = Votes(),
  comments: Seq[Comment] = Nil
) {
  def confidence = votes.confidence
  def impact = value * confidence
}
