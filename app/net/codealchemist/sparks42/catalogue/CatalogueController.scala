package net.codealchemist.sparks42.catalogue

import play.api.mvc.{Action, Controller}

class CatalogueController extends Controller {
  def articles = Action { implicit request =>
    InternalServerError
  }
}
