package services

import dominio.{Numeros}

import play.api.libs.json.{ JsPath, Json }
import play.api.libs.functional.syntax._
import play.api.libs.json._
trait MapeoNumerosCalculadora {

  implicit val leerNumeros: Format[Numeros] = Format[Numeros] (
    (
    (JsPath \ "numeroMayor").read[Int] and
      (JsPath \ "numeroMenor").read[Int] and
      (JsPath \ "operacion").read[String]
    )(Numeros.apply _),
    Json.writes[Numeros])

}
