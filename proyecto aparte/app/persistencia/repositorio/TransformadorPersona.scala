package persistencia.repositorio

import dominio.Persona
import persistencia.tablas.AgregarRegistroPersona
import scala.language.implicitConversions
object TransformadorPersona {

  implicit def dePersonaRegistro(persona: Persona) : AgregarRegistroPersona = {


    AgregarRegistroPersona(persona.nombre,persona.apellido,persona.cedula,persona.hobbies)

  }

}
