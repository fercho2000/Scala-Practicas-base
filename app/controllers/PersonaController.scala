package controllers
import dominio.Persona
import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import services.{MapeoPersonaServices, PersonaServicios}

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class PersonaController  @Inject() (personaServicios: PersonaServicios)(implicit executionContext: ExecutionContext) extends Controller  with MapeoPersonaServices {



  def guardarPersona = Action.async(BodyParsers.parse.json[Persona]) { request =>
    val resultado =   request.body
    personaServicios.guardarPersona(resultado).map{f =>

      if(f.isInstanceOf[Boolean]){
        Ok(Json.toJson("bien"))

      }else{

        Ok(Json.toJson("respuesta"))
      }
    }
      .recoverWith {
        case _: Exception => Future.successful( InternalServerError("No pudo guardarse el registro") )
     }
  }

}
