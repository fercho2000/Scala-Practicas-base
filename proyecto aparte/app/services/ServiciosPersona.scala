package services

import dominio.Persona
import javax.inject.Inject
import persistencia.repositorio.PersonaRepositorio

import scala.concurrent.Future

trait PersonaServicios {

  def guardarPersona(persona : Persona): Future[Any]
  def obtenerTodasPersonas() : Future[Seq[Persona]]
  def obtenerPersonaPorcedula(cedula : String) : Future[Seq[Persona]]
  def modificarPersona(persona: Persona) : Future[Any]
  def eliminarPersona(cedula: String) : Future[Unit]
}

class ServiciosPersona @Inject ()(personaRespositorio : PersonaRepositorio) extends PersonaServicios {

  def guardarPersona(persona: Persona) = {

    personaRespositorio.guardarPersona(persona)
  }

  def obtenerTodasPersonas()  = personaRespositorio.obtenerTodasPersonas()

  def obtenerPersonaPorcedula(cedula : String) = personaRespositorio.obtenerPersonaPorCedula(cedula)

  def modificarPersona(persona: Persona) = {

   personaRespositorio.modificarPersona(persona)

  }

  def eliminarPersona(cedula: String)  =    personaRespositorio.eliminarPersona(cedula);



}
