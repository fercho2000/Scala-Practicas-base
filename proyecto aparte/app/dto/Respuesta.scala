package dto

import dominio.Persona
import play.api.libs.json._


case class Respuesta(success: Boolean = true, mensaje: String, data: Seq[Persona])



case class Error(success: Boolean = false, mensaje: String)

object Respuesta {
  implicit val respuestaJson =  new Writes[Respuesta] {
    def writes(respuesta: Respuesta) = Json.obj(
      "success" -> respuesta.success,
      "mensaje" -> respuesta.mensaje,
      "data" -> respuesta.data.toString()
    )
  }
}