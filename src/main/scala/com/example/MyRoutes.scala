package com.example

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives._
import akka.http.scaladsl.server.directives.PathDirectives._
import akka.http.scaladsl.server.directives.RouteDirectives._

// this trait defines our service behavior independently from the service actor
trait MyRoutes {

  // we leave this abstract, since they will be provided by the App
  implicit def system: ActorSystem

  lazy val log = Logging(system, classOf[MyRoutes])

  lazy val myRoute: Route =
    path("hello") {
      get {
        complete {
          "world!"
        }
      }
    }
}