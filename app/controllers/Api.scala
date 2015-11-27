package controllers

import play.api._
import play.api.libs.json.Json
import play.api.mvc._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

import services.TopicService

class Api extends Controller {

  def check(url: String) = Action.async { req =>
    req.getQueryString("result") match {
      case Some(res) => Future.successful(Ok(Json.obj("confidence" -> res)))
      case None =>
        TopicService.getByUrl(url).map {
          case Some(topic) => Ok(Json.obj("confidence" -> Option(topic.confidence)))
          case None => NotFound(Json.obj("confidence" -> Option.empty[Double]))
        }

    }
  }

}
