package com.github.rodrigodealer.client

import com.twitter.finagle.http.Status
import com.twitter.util.Await
import org.scalatest.{FlatSpec, Matchers}

class HttpClientTest extends FlatSpec with Matchers {
  "HttpClient" should "perform" in {
    class MyClass extends HttpClient {}

    val klazz = new MyClass

    val requestToPerform = GetRequest("google.com", "google.com", true)

    val request = Await.result(klazz.perform(requestToPerform))

    request.status shouldBe Status.MovedPermanently
  }
}
