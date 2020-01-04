package services

import dominio.Persona
import javax.inject.Inject
import persistencia.repositorio.PersonaRepositorio

import scala.concurrent.Future

class ServiciosPersona @Inject ()(personaRespositorio : PersonaRepositorio) extends PersonaServicios {

   def guardarPersona(persona: Persona) = {

     personaRespositorio.guardarPersona(persona)

  }
}
