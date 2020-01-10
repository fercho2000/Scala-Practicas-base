package persistencia.repositorio

import dominio.Persona
import javax.inject.{Inject, Singleton}
import persistencia.tablas._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile
import persistencia.repositorio.TransformadorPersona._
import play.db.NamedDatabase

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class PersonaRepositorio @Inject() (@NamedDatabase("redcomercial") val dbConfigProvider : DatabaseConfigProvider)(implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile]  {
  import driver.api._
  private val tablaPersona = TableQuery[TablaPersona]
  private val tablaspersonas = TableQuery[PersonasTablaLista]



  def guardarPersona(persona: Persona): Future[Unit] =  db.run(tablaPersona += persona).map { _ => ()}

  def obtenerPersonaPorCedula(cedula : String) : Future[Seq[Persona]] = db.run(tablaspersonas.filter(_.cedula===cedula).result)

  def obtenerTodasPersonas() : Future[Seq[Persona]] = db.run(tablaspersonas.result)

  def modificarPersona(persona: Persona)  : Future[Unit] =  db.run {
    val q = for {c <- tablaspersonas if c.cedula === persona.cedula } yield c
    q.update(persona)
  }.map { _ => ()}

  def eliminarPersona(cedula: String): Future[Unit] = db.run {
    val datos = for {consulta <-tablaspersonas if consulta.cedula === cedula } yield consulta

    datos.delete
  }.map{_ => () }

}
