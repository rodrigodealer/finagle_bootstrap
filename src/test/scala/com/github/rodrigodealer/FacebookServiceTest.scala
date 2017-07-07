package com.github.rodrigodealer

import com.github.rodrigodealer.client.{HttpClient, RequestToPerform}
import com.github.rodrigodealer.model.FacebookToken
import com.twitter.finagle.http
import com.twitter.finagle.http.{Response, Status}
import com.twitter.finagle.netty3.ChannelBufferBuf
import com.twitter.util.{Await, Future}
import org.jboss.netty.buffer.ChannelBuffers.copiedBuffer
import org.scalatest.{FlatSpec, Matchers}

class FacebookServiceTest extends FlatSpec with Matchers {


  "Facebook service" should "getToken" in {
    val facebookService = new FacebookService with DummyHttpClientOk
    val token = Await.result(facebookService.getToken)
    token shouldBe Some(FacebookToken("1203725573077981|HrXuRe8Ph7hzzGHH-8ATjMMlojg","bearer"))
  }

  "Facebook service" should "not getToken" in {
    val facebookService = new FacebookService with DummyHttpClientFailure
    val token = Await.result(facebookService.getToken)
    token shouldBe None
  }

  trait DummyHttpClientOk extends HttpClient {
    override def perform(requestToPerform: RequestToPerform) : Future[http.Response]  = {
      val response = Response()
      response.status = Status.Ok
      response.content(new ChannelBufferBuf(copiedBuffer("{\"access_token\":\"1203725573077981|HrXuRe8Ph7hzzGHH-8ATjMMlojg\",\"token_type\":\"bearer\"}".getBytes)))
      Future(response)
    }
  }

  trait DummyHttpClientFailure extends HttpClient {
    override def perform(requestToPerform: RequestToPerform) : Future[http.Response]  = {
      val response = Response()
      response.status = Status.Ok
      Future(response)
    }
  }
}
