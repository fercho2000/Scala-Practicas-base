package persistencia.repositorio

import dominio.Persona
import persistencia.tablas.AgregarRegistroPersona
object TransformadorPersona {

  implicit def dePersonaRegistro(persona: Persona) : AgregarRegistroPersona = {

    /*val hobbie = persona.hobbies.getOrElse(null)*/

    AgregarRegistroPersona(persona.nombre,persona.apellido,persona.cedula,persona.hobbies)

  }

}
