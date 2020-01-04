package persistencia.repositorio


import dominio.Persona
import javax.inject.{Inject, Singleton}
import persistencia.repositorio.TransformadorPersona._
import persistencia.tablas._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.db.NamedDatabase
import slick.driver.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}


@Singleton
class PersonaRepositorio @Inject() (@NamedDatabase("redcomercial") val dbConfigProvider : DatabaseConfigProvider)(implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile]  {

  import driver.api._

  private val tablaPersona = TableQuery[TablaPersona]

    def guardarPersona(persona: Persona): Future[Unit] =  db.run(tablaPersona += persona).map { _ => ()}

}
