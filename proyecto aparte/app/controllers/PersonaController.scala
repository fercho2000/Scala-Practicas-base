package controllers


import dominio.Persona
import dto.Respuesta
import javax.inject._
import play.Logger
import play.api.mvc._
import play.api.libs.json._
import services.{MapeoPersonaServices, ServiciosPersona}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class PersonaController @Inject()(personaServicios: ServiciosPersona)(implicit executionContext: ExecutionContext) extends Controller with MapeoPersonaServices {
  val MENSAJEREGISTRO = "La persona se registro con éxito"
  val ERRORALREGISTRAR = "No pudo guardarse el registro"
  val LISTADEPERSONAS = "Personas recuperadas correctamente"
  val MENSAJEPERSONAELIMINADA = "Persona eliminada correctamente"
  val ERRORALELIMINAR = "No pudo eliminarse el registro"

  def guardarPersona = Action.async(parse.json[Persona]) { request =>
    val resultado = request.body
    personaServicios.guardarPersona(resultado).map { f =>
      val respuesta = Respuesta(true, MENSAJEREGISTRO, Seq(resultado))
      Ok(Json.toJson(respuesta))
    }
      .recoverWith {
        case error: Exception =>
          Logger.error("No se guardo el registro", error)
          Future.successful(InternalServerError(ERRORALREGISTRAR))
      }
  }

  def obtnerTodasLasPersonas() = Action.async { implicit request: Request[AnyContent] =>
    val personasFuture = personaServicios.obtenerTodasPersonas
    personasFuture.map { s =>
      val respuesta = Respuesta(true, LISTADEPERSONAS, s)
      Ok(Json.toJson(respuesta))
    }

  }

  def obtenerPersonaPorCedula(cedula: String) = Action.async { implicit request: Request[AnyContent] =>
    val personaFuture = personaServicios.obtenerPersonaPorcedula(cedula)
    personaFuture.map { s =>
      if (s.size == 1) {
        val respuesta = Respuesta(true, "Persona recuperada correctamente", s)
        Ok(Json.toJson(respuesta))
      } else {
        val respuesta = Respuesta(false, "Persona no encontrada", s)
        NotFound(Json.toJson(respuesta))
      }
    }
  }


  def modificarPersona() = Action.async(parse.json[Persona]) { request =>
    val persona = request.body
    personaServicios.modificarPersona(persona)
      .map { f =>

        val respuesta = Respuesta(true, "Persona modificada correctamente", Seq(persona))
        Ok(Json.toJson(respuesta))
      }
      .recoverWith {
        case _: Exception => Future.successful(InternalServerError("No pudo modificarse el registro"))
      }
  }

  def eliminarPersonaPorcedula(cedula: String) = Action.async {

    personaServicios.eliminarPersona(cedula).map { _ =>

      val persona = Persona("", "", ""+personaServicios.eliminarPersona(cedula), null)

      val respuesta = Respuesta(true, MENSAJEPERSONAELIMINADA, Seq(persona))

      Ok(Json.toJson(respuesta))

    }
      .recoverWith {
        case _: Exception => Future.successful(InternalServerError(ERRORALELIMINAR))
      }

  }

}
