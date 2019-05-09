package com.github.rodrigodealer

import com.github.rodrigodealer.client.{HttpClient, RequestToPerform}
import com.github.rodrigodealer.model.{BasicFacebookUser, FacebookToken}
import com.twitter.finagle.http
import com.twitter.finagle.http.{Response, Status}
import com.twitter.io.Buf
import com.twitter.util.{Await, Future}
import org.scalatest.{FlatSpec, Matchers}

class FacebookServiceTest extends FlatSpec with Matchers {


  "Facebook service" should "getToken" in {

    trait DummyHttpClientOk extends HttpClient {
      override def perform(requestToPerform: RequestToPerform) : Future[http.Response]  = {
        val response = Response()
        response.status = Status.Ok
        response.content(Buf.Utf8("{\"access_token\":\"1203725573077981|HrXuRe8Ph7hzzGHH-8ATjMMlojg\",\"token_type\":\"bearer\"}"))
        Future(response)
      }
    }

    val facebookService = new FacebookService with DummyHttpClientOk
    val token = Await.result(facebookService.getToken)
    token shouldBe Some(FacebookToken("1203725573077981|HrXuRe8Ph7hzzGHH-8ATjMMlojg","bearer"))
  }

  "Facebook service" should "not getToken" in {

    trait DummyHttpClientFailure extends HttpClient {
      override def perform(requestToPerform: RequestToPerform) : Future[http.Response]  = {
        val response = Response()
        response.status = Status.Ok
        Future(response)
      }
    }

    val facebookService = new FacebookService with DummyHttpClientFailure
    val token = Await.result(facebookService.getToken)
    token shouldBe None
  }

  "Facebook service" should "facebookCheck" in {

    trait DummyHttpClientOk extends HttpClient {
      override def perform(requestToPerform: RequestToPerform) : Future[http.Response]  = {
        val response = Response()
        response.status = Status.Ok
        response.content(Buf.Utf8("{\"name\":\"1\",\"id\":\"2\"}"))
        Future(response)
      }
    }

    val facebookService = new FacebookService with DummyHttpClientOk
    val token = Await.result(facebookService.facebookCheck("123"))
    token shouldBe Some(BasicFacebookUser("1","2"))
  }

  "Facebook service" should "not facebookCheck" in {

    trait DummyHttpClientFailure extends HttpClient {
      override def perform(requestToPerform: RequestToPerform) : Future[http.Response]  = {
        val response = Response()
        response.status = Status.Ok
        Future(response)
      }
    }

    val facebookService = new FacebookService with DummyHttpClientFailure
    val token = Await.result(facebookService.facebookCheck("123"))
    token shouldBe None
  }
}
