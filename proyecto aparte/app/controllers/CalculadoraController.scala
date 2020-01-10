package controllers

import javax.inject.{Inject, Singleton}
import play.api.libs.json.{JsError, Json}
import play.api.mvc._
import services.{MapeoNumerosCalculadora, ServicioCalculos}
import dominio.Numeros
import dto.Error

import scala.concurrent.ExecutionContext

@Singleton
class CalculadoraController  @Inject()(servicioCalcular : ServicioCalculos)(implicit executionContext: ExecutionContext)
  extends Controller with MapeoNumerosCalculadora{

    def calcularOperacion = Action(BodyParsers.parse.json) { request =>
      val jsonResult = request.body.validate[Numeros]
      jsonResult.fold(
        errors => {
          BadRequest(Json.obj("status" ->"Error", "message" -> ("errors"+errors)))
        },
        numerosAcalcular => {
           val resultadoOperacion = servicioCalcular.calcularResultado(numerosAcalcular)

          resultadoOperacion match {
            case Left(err: Error) => BadRequest(Json.obj("estado" ->"Error", "mensaje" ->("Resultado : " +resultadoOperacion)))
            case Right(value) =>  Ok(Json.obj("estado" ->"OK", "mensaje" ->("Resultado de  "+numerosAcalcular.operacion +" es :"+resultadoOperacion)))
          }

        }
      )
    }

}
