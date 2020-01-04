package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._

import scala.util.Try

@Singleton
class CalculadoraController @Inject()extends Controller{

  case class Numeros(numeroMayor: Int, numeroMenor: Int)

  object Numeros {
    def calcularSumar(numeros: Numeros) = {
     numeros.numeroMayor + numeros.numeroMenor;

    }

    def  calcularResta(numeros: Numeros) = {
      numeros.numeroMayor - numeros.numeroMenor;

    }

    def calcularMultiplicacion(numeros: Numeros) = {
       numeros.numeroMayor * numeros.numeroMenor;

    }

    def calcularDivision(numeros: Numeros) =
      Try(numeros.numeroMayor/numeros.numeroMenor).getOrElse("La division por cero no es posible")
  }



  def savePlace = Action(BodyParsers.parse.json) { request =>
    val placeResult = request.body.validate[Numeros]
    placeResult.fold(
      errors => {
        BadRequest(Json.obj("status" ->"KO", "message" -> JsError.toJson(errors)))
      },
      numerosAcalcular => {
        val resultadoSuma = Numeros.calcularSumar(numerosAcalcular);
        val resultadoResta = Numeros.calcularResta(numerosAcalcular);
        val resultadoMultiplicar = Numeros.calcularMultiplicacion(numerosAcalcular);
        val resultadoDividir = Numeros.calcularDivision(numerosAcalcular);

        Ok(Json.obj("estado" ->"OK", "mensaje" ->
          ( "La suma es : "+(resultadoSuma)+
            ", La resta es : "+ (resultadoResta)+
            ", La multiplicación es : "+ (resultadoMultiplicar)+
            ", La división es : "+ (resultadoDividir) ) ))
      }
    )
  }



}
