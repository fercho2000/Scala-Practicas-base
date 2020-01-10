package persistencia.tablas
import java.sql.Date
import dominio.Persona
import slick.driver.PostgresDriver.api._



class TablaPersona(tag: Tag) extends Table[AgregarRegistroPersona](tag, Some("asesores"), "persona") {


  def nombre = column[String]("nombre")

  def cedula = column[String]("cedula", O.PrimaryKey)

  def apellido = column[String]("apellido")

  def hobbies = column [Option[String]]("hobbies")

  def * =
    (nombre,
      apellido,
      cedula,
      hobbies) <> (AgregarRegistroPersona.tupled, AgregarRegistroPersona.unapply)
}

case class AgregarRegistroPersona(
                                   nombre : String,
                                   apellido: String,
                                   cedula: String,
                                   hobbies : Option[String])


 class PersonasTablaLista(tag: Tag) extends Table[Persona](tag, Some("asesores"), "persona") {
  def nombre = column[String]("nombre")

  def cedula = column[String]("cedula", O.PrimaryKey)

  def apellido = column[String]("apellido")

  def hobbies = column [Option[String]]("hobbies")

  def * =
    (nombre,
      apellido,
      cedula,
      hobbies) <> (Persona.tupled, Persona.unapply)

}

