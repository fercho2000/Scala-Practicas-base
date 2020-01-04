package services

import dominio.Persona
import org.joda.time.DateTime
import play.api.libs.json.{ JsPath, Json }
import play.api.libs.functional.syntax._
import play.api.libs.json._
trait MapeoPersonaServices  {




  implicit val personaFormat: Format[Persona] = Format[Persona](
    (
      (JsPath \ "nombre").read[String] and
        (JsPath \ "apellido").read[String] and
        (JsPath \ "cedula").read[String] and
        (JsPath \ "hobbies").readNullable[String]
      )(Persona.apply _),
    Json.writes[Persona])

}
