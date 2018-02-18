package com.example

import akka.http.scaladsl.model.StatusCodes.PermanentRedirect
import akka.http.scaladsl.model.Uri
import akka.http.scaladsl.model.headers.Location
import akka.http.scaladsl.testkit.RouteTest
import akka.http.scaladsl.testkit.TestFrameworkInterface.Scalatest
import org.scalatest.{AsyncWordSpec, Matchers}

/**
  * Created by jason on 2/17/18.
  */
final class WebRoutesSpec extends AsyncWordSpec with Matchers with RouteTest with WebRoutes with Scalatest {

  "Route" should {

    "respond with PermanentRedirect to index.html upon a 'GET /'" in {
      Get() ~> webappRoute ~> check {
        status shouldBe PermanentRedirect
        header[Location] shouldBe Some(Location(Uri("index.html")))
      }
    }

  }

}
