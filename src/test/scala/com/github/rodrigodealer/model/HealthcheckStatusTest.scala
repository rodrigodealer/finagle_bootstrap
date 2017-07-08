package com.github.rodrigodealer.model

import com.twitter.finagle.http.Status
import org.scalatest.{FlatSpec, Matchers}

class HealthcheckStatusTest extends FlatSpec with Matchers {

  "HealthcheckStatus" should "healthcheckMessage WORKING" in {
    val status = HealthcheckStatus.healthcheckMessage(true)
    status shouldBe "WORKING"
  }

  "HealthcheckStatus" should "healthcheckMessage FAILED" in {
    val status = HealthcheckStatus.healthcheckMessage(false)
    status shouldBe "FAILED"
  }

  "HealthcheckStatus" should "healthcheckStatusCode OK" in {
    val status = HealthcheckStatus.healthcheckStatusCode(true)
    status shouldBe Status.Ok
  }

  "HealthcheckStatus" should "healthcheckStatusCode InternalServerError" in {
    val status = HealthcheckStatus.healthcheckStatusCode(false)
    status shouldBe Status.InternalServerError
  }

}
