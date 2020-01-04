package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc.{Action, Controller}

@Singleton
class Devolver200 @Inject()  extends Controller{

  def devolverMensajeExito = Action {
    Ok(views.html.index("Retornamos un 200"))
  }



}
