package com.github.rodrigodealer

import com.github.rodrigodealer.model.HealthcheckItem
import com.twitter.finagle.http.Status
import org.scalatest.{FlatSpec, Matchers}

class ResponsesTest extends FlatSpec with Matchers {

  "Response" should "have json" in {
    val health = HealthcheckItem("test", "severe", true)

    val response = Responses.json(health, Status.Ok)
    response.status shouldBe Status.Ok
    response.contentType shouldBe Some("application/json;charset=utf-8")
    response.contentString shouldBe "{\"name\":\"test\",\"severity\":\"severe\",\"status\":true}"
  }
}
