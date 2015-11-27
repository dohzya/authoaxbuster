package services

import scala.concurrent.Future

import models.{ Answer, Comment, Topic, Votes }

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
      urls = Seq(
        "http://truc.com"
      ),
      comments = Seq(
        Comment("Hey, I can only agree with you"),
        Comment("Same here"),
        Comment("+1")
      ),
      answers = Seq(
        Answer(
          value = -1,
          content = """You can use the :last selector:

    $(".source").appendTo(".myClass:last");

Updated fiddle""",
          votes = Votes(53, 3),
          comments = Seq(Comment("Nice catch"))
        ),
        Answer(
          value = 1,
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
      answers = Seq(Answer(), Answer(), Answer(), Answer()),
      nViews = 73
    ),
    Topic(
      title = "Les ours polaires seront roux dans 5 ans",
      desc = "bla bla bla bla"
    )
  )
  private def sync[A](f: => A): Future[A] = synchronized { Future.successful(f) }

  def getLast(): Future[Seq[Topic]] = sync { all.take(10) }

  def getById(id: String): Future[Option[Topic]] = sync { all.find(_.id == id) }

  def getByUrl(url: String): Future[Option[Topic]] = sync { all.find(_.urls.contains(url)) }

}
