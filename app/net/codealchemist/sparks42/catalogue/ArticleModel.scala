package net.codealchemist.sparks42.catalogue

import net.codealchemist.sparks42.catalogue.Represenation.Article
import slick.driver.SQLiteDriver.api._

object ArticleModel {
  val db = Database.forConfig("sqlite")

  case class ArticleWithId(id: Long, name: String, brand: String, price: BigDecimal, imageUrl: String) {
    def toArticle = Article(name, brand, price, imageUrl)
  }

  class Articles(tag: Tag) extends Table[ArticleWithId](tag, "SUPPLIERS") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc) // This is the primary key column
    def name = column[String]("name")
    def brand = column[String]("brand")
    def price = column[BigDecimal]("price")
    def imageUrl = column[String]("imageUrl")

    def * = (id, name, brand, price, imageUrl) <> (ArticleWithId.tupled, ArticleWithId.unapply)
  }

  val articles = TableQuery[Articles]

  def getArticles = db.run(articles.result)

  def getArticlesSorted(sortBy: Column, direction: Direction = Ascending) = {
    val q = articles.sortBy { article =>
      val column = sortBy match {
        case Name => article.name.asc
        case Price => article.price.asc
      }

      direction match {
        case Ascending => column
        case Descending => column.reverse
      }
    }

    db.run(q.result)
  }

  sealed trait Column
  case object Name extends Column
  case object Price extends Column

  sealed trait Direction
  case object Ascending extends Direction
  case object Descending extends Direction
}
