package services
import dominio.Persona

import scala.concurrent.Future
trait PersonaServicios {

  def guardarPersona(persona : Persona): Future[Any]

}
