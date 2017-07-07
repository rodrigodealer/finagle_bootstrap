package com.github.rodrigodealer

import com.github.rodrigodealer.HealthcheckService.{apiKey, apiSecret}
import com.github.rodrigodealer.client.HttpClient
import com.github.rodrigodealer.json.JsonUtil.fromJson
import com.github.rodrigodealer.json.Responses
import com.github.rodrigodealer.model.{BasicFacebookUser, FacebookToken}
import com.twitter.finagle.http.{Request, Response, Status}
import com.twitter.finagle.{Service, http}
import com.twitter.finagle.http.Status.Ok
import com.twitter.util.Future

object FacebookService extends HttpClient {

  def apply(uid: String): Service[Request, Response] = {
    (request: http.Request) => {
      facebookCheck(uid) flatMap { user =>
        val response = Responses.json(user, Status.Ok)
        Future(response)
      }
    }
  }

  val apiKey = "1203725573077981"
  val apiSecret = "7fb858f4301f919ea56b97e70bbfe965"

  def getToken : Future[Option[FacebookToken]] = {
    val url = s"/oauth/access_token?client_id=$apiKey&client_secret=$apiSecret&grant_type=client_credentials"
    val client: Service[http.Request, http.Response] = httpsClient("graph.facebook.com")
    val request = http.Request(http.Method.Get, url)
    request.host = "graph.facebook.com"
    val response: Future[http.Response] = client(request)
    response.flatMap { token =>
      token.status match {
        case Ok => Future(Option(fromJson[FacebookToken](token.contentString)))
        case _ => Future(Option.empty)
      }
    } rescue { case _ => Future(Option.empty)}
  }

  def facebookCheck(uid: String) : Future[Option[BasicFacebookUser]] = getToken flatMap {
    case Some(token) =>  {
      val url = s"/v2.9/$uid?access_token=${token.access_token}"
      val client: Service[http.Request, http.Response] = httpsClient("graph.facebook.com")
      val request = http.Request(http.Method.Get, url)
      request.host = "graph.facebook.com"
      val response: Future[http.Response] = client(request)
      response flatMap { resp =>
        Future(Option(fromJson[BasicFacebookUser](resp.contentString)))
      } rescue { case _ => Future(Option.empty)}
    }
    case None => Future(Option.empty)
  } rescue { case _ => Future(Option.empty)}
}