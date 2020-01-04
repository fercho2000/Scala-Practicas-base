package controllers

import akka.actor.ActorSystem
import javax.inject._
import play.api.mvc._
import scala.concurrent.{ExecutionContext, Future, Promise}
import scala.concurrent.duration._

@Singleton
class Devolver401  @Inject() (actorSystema: ActorSystem)(implicit exec: ExecutionContext) extends Controller{

  def devolverNoAurotizado  = Action.async {

    resolverFuturoMensaje(1.second).map { msg => Unauthorized(msg) }
  }

  private  def resolverFuturoMensaje(delayTime: FiniteDuration): Future[String] = {
    val promise: Promise[String] = Promise[String]()
    actorSystema.scheduler.scheduleOnce(delayTime) { promise.success("No tienes permisos para estar aquí! 401") }
    promise.future
  }


}
