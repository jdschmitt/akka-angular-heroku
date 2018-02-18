package com.example

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import akka.util.Timeout

import scala.concurrent.Await
import scala.concurrent.duration.{Duration, _}
import scala.util.Properties

object Boot extends App with MyRoutes with WebRoutes {

  // we need an ActorSystem to host our application in
  implicit val system = ActorSystem("akka-angular-heroku")
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  override lazy val log = Logging(system, classOf[App])

  lazy val routes: Route = webappRoute ~ myRoute

  implicit val timeout = Timeout(5.seconds)
  // Check for assigned port (heroku support) or default to 8080
  val port = Properties.envOrElse("PORT", "8080").toInt

  // Interface MUST be 0.0.0.0 for heroku
  Http().bindAndHandle(routes, "0.0.0.0", port)

  println(s"Server online at http://localhost:8080/")

  Await.result(system.whenTerminated, Duration.Inf)
}
