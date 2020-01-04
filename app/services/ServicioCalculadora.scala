package services

class ServicioCalculadora {

  case class Suma(numero1: Int, numero2: Int)
  object Suma {

    def calcular(numeros: Suma) = {
      val calculoSuma = numeros.numero1 + numeros.numero2;
      calculoSuma
    }
  }
}
