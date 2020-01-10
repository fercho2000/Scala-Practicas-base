package services

import javax.inject.Inject
import dominio.Numeros
import dto.Error

import scala.util.{Failure, Success, Try}

trait Operaciones {

  def suma(numeroMayro: Int, numeroMenor: Int): Any

  def resta(numeroMayro: Int, numeroMenor: Int): Any

  def multiplicacion(numeroMayro: Int, numeroMenor: Int): Any

  def division(numeroMayro: Int, numeroMenor: Int): Any

}

class ServicioCalculos @Inject() extends Operaciones {

  def suma(numeroMayro: Int, numeroMenor: Int): Any = {

    Try(numeroMayro + numeroMenor) match {
    case Success(value) => Right (value)
    case Failure(exception) => Left( Error(mensaje = s"Error al sumar ${exception.getLocalizedMessage}"))
    }
  }

  def resta(numeroMayro: Int, numeroMenor: Int): Any = {

    Try(numeroMayro - numeroMenor) match {
      case Success(value) => Right (value)
      case Failure(exception) => Left( Error(mensaje = s"Error Al restar ${exception.getLocalizedMessage}"))
    }
  }

  def multiplicacion(numeroMayro: Int, numeroMenor: Int): Any = {
    Try(numeroMayro * numeroMenor) match {
      case Success(value) => Right (value)
      case Failure(exception) => Left( Error(mensaje = s"Error al multilicar ${exception.getLocalizedMessage}"))
    }
  }

  def division(numeroMayro: Int, numeroMenor: Int): Any = {

    Try(numeroMayro / numeroMenor) match {
      case Success(value) => Right (value)
      case Failure(exception) => Left( Error(mensaje = s"La division por cero no es posible ${exception.getLocalizedMessage}"))
    }

  }

  def calcularResultado(numeros: Numeros) = numeros.operacion match {
    case ("suma") => suma(numeros.numeroMayor, numeros.numeroMenor)
    case ("restar") => resta(numeros.numeroMayor, numeros.numeroMenor)
    case ("multiplicar") => multiplicacion(numeros.numeroMayor, numeros.numeroMenor)
    case ("division") => division(numeros.numeroMayor, numeros.numeroMenor)
    case _ => "Operación incorrecta"
  }

}
