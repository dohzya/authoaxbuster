package services

import scala.concurrent.Future

import models.{ Answer, Comment, Topic, Votes }

object TopicService {

  private val all = Seq(
    Topic(
      id = "uOrnCvEHnT",
      title = "Il faut verser le lait avant les céréales",
      desc = """Les services secrets arméniens conseillent de verser le lait _avant_ les céréales et menacent les contrevenants de remplacer leur lait par du coca.""",
      urls = Seq(
        "http://truc.com"
      ),
      comments = Seq(
        Comment("Comme même c'est fou"),
        Comment("Moi je fais toujours comme ça donc je m'en fou"),
        Comment("QUOI QUOI QUOI?")
      ),
      answers = Seq(
        Answer(
          value = 1,
          content = """Moi je préfère y mettre du chocolat au lait""",
          votes = Votes(1, 212),
          comments = Seq(
            Comment("Euh… on s'en fout ?"),
            Comment("Moi ça m'a ému :'-)")
          )
        ),
        Answer(
          value = -1,
          content = "C'est n'imp'. En Arménie on boit de la bière au petit déj.",
          votes = Votes(34, 5),
          comments = Seq(
            Comment("Je dois avoir du sang arménien !"),
            Comment("Moa oci mdr")
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
