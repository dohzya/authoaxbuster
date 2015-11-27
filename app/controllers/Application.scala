package controllers

import java.time.ZonedDateTime
import play.api._
import play.api.mvc._
import scala.concurrent.ExecutionContext.Implicits.global

import services.TopicService

class Application extends Controller {

  val mdProcessor = new org.pegdown.PegDownProcessor(
    org.pegdown.Extensions.SUPPRESS_ALL_HTML
  )
  val md2html = mdProcessor.markdownToHtml(_: String)

  def index = Action.async {
    TopicService.getLast().map { topics =>
      Ok(views.html.index(topics))
    }
  }

  def show(id: String) = Action.async {
    TopicService.getById(id).map {
      case Some(topic) => Ok(views.html.show(topic, md2html))
      case None => NotFound("Not found")
    }
  }

}
