package controllers

import akka.actor.ActorSystem
import javax.inject._
import play.api.mvc._
import scala.concurrent.{ExecutionContext, Future, Promise}
import scala.concurrent.duration._

@Singleton
class Devolver500  @Inject() (actorSystema: ActorSystem)(implicit exec: ExecutionContext) extends Controller{

  def devolverError  = Action.async {

    resolverFuturoMensaje500(1.second).map { msg => InternalServerError(msg).withCookies(Cookie("Nombre de cookie","Valor de la cookie")) }
  }

  private  def resolverFuturoMensaje500(delayTime: FiniteDuration): Future[String] = {
    val promise: Promise[String] = Promise[String]()
    actorSystema.scheduler.scheduleOnce(delayTime) { promise.success("Internal error 500!") }
    promise.future
  }

}
