package com.example

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import akka.util.Timeout
import spray.can.Http

import scala.concurrent.duration._
import scala.util.Properties

object Boot extends App {

  // we need an ActorSystem to host our application in
  implicit val system = ActorSystem("spray-angular2-heroku")

  // create and start our service actor
  val service = system.actorOf(Props[MyServiceActor], "my-service")

  implicit val timeout = Timeout(5.seconds)
  // Check for assigned port (heroku support) or default to 8080
  val port = Properties.envOrElse("PORT", "8080").toInt

  // Interface MUST be 0.0.0.0 for heroku
  IO(Http) ! Http.Bind(service, interface = "0.0.0.0", port = port)
}
