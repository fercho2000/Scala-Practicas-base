package persistencia.tablas


import slick.driver.PostgresDriver.api._

 class TablaPersona(tag: Tag) extends Table[AgregarRegistroPersona](tag, "persona") {

  def nombre = column[String]("nombre")

  def cedula = column[String]("cedula", O.PrimaryKey)

  def apellido = column[String]("apellido")

  def hobbies = Option[String]("hobbies")

   def * =
    (nombre,
      cedula,
      apellido,
      hobbies) <> (AgregarRegistroPersona.tupled, AgregarRegistroPersona.unapply)
}

  case class AgregarRegistroPersona(
          nombre : String,
          apellido: String,
          cedula: String,
          hobbies : Option[String])

