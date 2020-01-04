package dominio


import play.api.libs.json.Json

case class Persona(nombre : String, apellido: String, cedula: String,  hobbies : Option[String] )

object Persona {
  implicit val personaFormat= Json.format[Persona]
}