package services

import scala.concurrent.Future

import models.{ Answer, Comment, Note, Topic }

object TopicService {

  private val all = Seq(
    Topic(
      id = "uOrnCvEHnT",
      title = "Il faut verser le lait avant les céréales",
      desc = """I want to append an element to another element. Problem is that the 'target element' is only identified by a class and this class is used twice. **In my case i want to append to the last/second presence of the element.** Check fiddle here for a visualization

This should _not_ be a link: <a href="hehe">JSFiddle</a>.

## Start point:

    <div class="container">
        <div class="myClass">This is a <strong>not</strong> the target</div>
        <div class="myClass">This is the target</div>
    </div>
    <div class="container2">
        <div class="source">This is the source</div>
    </div>

Which appends ".source" to both divs with the class ".myClass".""",
      note = Note(10, 2),
      comments = Seq(
        Comment("Hey, I can only agree with you"),
        Comment("Same here"),
        Comment("+1")
      ),
      answers = Seq(
        Answer(
          content = """You can use the :last selector:

    $(".source").appendTo(".myClass:last");

Updated fiddle""",
          note = Note(53, 3),
          comments = Seq(Comment("Nice catch"))
        ),
        Answer(
          content = "Honestly, I really don't know...",
          comments = Seq(
            Comment("Then why did you answered?"),
            Comment("That naab, lol")
          )
        )
      ),
      nViews = 23
    ),
    Topic(
      title = "Des géants retrouvés au Caire",
      desc = "bla bla bla bla",
      note = Note(10, 5),
      answers = Seq(Answer(), Answer(), Answer(), Answer()),
      nViews = 73
    ),
    Topic(
      title = "Les ours polaires seront roux dans 5 ans",
      desc = "bla bla bla bla"
    )
  )
  private def update[A](f: => A): Future[A] = synchronized { Future.successful(f) }

  def getLast(): Future[Seq[Topic]] = update { all.take(10) }

  def getById(id: String): Future[Option[Topic]] = update { all.find(_.id == id) }

}
