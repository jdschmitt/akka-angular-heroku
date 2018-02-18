package com.example

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.testkit.RouteTest
import akka.http.scaladsl.testkit.TestFrameworkInterface.Scalatest
import org.scalatest.{AsyncWordSpec, Matchers}

/**
  * Created by jason on 2/17/18.
  */
final class MyRoutesSpec extends AsyncWordSpec with Matchers with RouteTest with MyRoutes with Scalatest {

  "Route" should {

    "respond with PermanentRedirect to index.html upon a 'GET /'" in {
      Get("/hello") ~> myRoute ~> check {
        responseAs[String] shouldBe "world!"
      }
    }

    "leave GET requests to other paths unhandled" in {
      Get("/kermit") ~> myRoute ~> check {
        handled shouldBe false
      }
    }

    "return a MethodNotAllowed error for PUT requests to the root path" in {
      Put("/hello") ~> Route.seal(myRoute) ~> check {
        status shouldEqual StatusCodes.MethodNotAllowed
        responseAs[String] shouldBe "HTTP method not allowed, supported methods: GET"
      }
    }

  }

}
