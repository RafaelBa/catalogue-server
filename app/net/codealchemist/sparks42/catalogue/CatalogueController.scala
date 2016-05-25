package net.codealchemist.sparks42.catalogue

import javax.inject.Inject
import net.codealchemist.sparks42.catalogue.Represenation.articleWriter
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

class CatalogueController @Inject() (zalando: ZalandoService) extends Controller {
  def articles = Action { implicit request =>
    Ok(Json toJson (zalando.articles))
  }
}
