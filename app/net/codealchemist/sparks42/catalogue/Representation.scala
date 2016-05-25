package net.codealchemist.sparks42.catalogue

import play.api.libs.functional.syntax._
import play.api.libs.json.{Json, Reads, __}

object Represenation {
  case class Article(name: String, brand: String, price: BigDecimal, imageUrl: String)

  implicit val articleReader: Reads[Article] = (
    (__ \ "name").read[String] and
      (__ \ "brand" \ "name").read[String] and
      (__ \ "units" \ (0) \ "price" \"value").read[BigDecimal] and
      (__ \ "media" \ "images" \ (0) \ "mediumUrl").read[String]
  )(Article.apply _)

  implicit val articleWriter = Json.writes[Article]
}
