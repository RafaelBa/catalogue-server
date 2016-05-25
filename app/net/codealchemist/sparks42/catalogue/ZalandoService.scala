package net.codealchemist.sparks42.catalogue

import javax.inject.Inject
import net.codealchemist.sparks42.catalogue.Represenation.Article
import play.api.libs.concurrent.Execution.Implicits._
import play.api.libs.json.{JsError, JsSuccess}
import play.api.libs.ws._
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

class ZalandoService @Inject() (ws: WSClient) {
  def articles: List[Article] = {
    val baseUrl = "https://api.zalando.com/articles?page="
    val requests = for {
      i <- 1 to 4
    } yield ws.url(baseUrl + i).get

    val articles = Future.sequence(requests).map(_.toList.flatMap { res =>
      ((res.json) \ "content").validate[List[Article]] match {
        case JsSuccess(value, _) => value
        case err: JsError => List.empty
      }
    })

    Await.result(articles, 10 seconds)
  }
}
