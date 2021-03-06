package com.github.rodrigodealer.client

import com.twitter.finagle.{Http, Service, http}
import com.twitter.util.Future

trait HttpClient {
  def httpxClient(host: String, tls: Boolean = true) : Service[http.Request, http.Response] = {

    val service : Service[http.Request, http.Response] = tls match {
      case true => Http
        .client
        .withTls(host)
        .newService(s"$host:443")
      case false => Http
        .client
        .newService(s"$host:8080")
    }
    service
  }

  def perform(requestToPerform: RequestToPerform) : Future[http.Response] = {
    val client: Service[http.Request, http.Response] = httpxClient(requestToPerform.host, requestToPerform.tls)
    val request = http.Request(requestToPerform.method, requestToPerform.url)
    request.host = requestToPerform.host
    client(request)
  }
}

case class GetRequest(url: String,
                      host: String,
                      override val tls: Boolean,
                      method: http.Method = http.Method.Get) extends RequestToPerform

case class PostRequest(url: String,
                       host: String,
                       override val data: Option[Any],
                       override val tls: Boolean,
                       method: http.Method = http.Method.Post) extends RequestToPerform

sealed trait RequestToPerform {
  val url: String
  val host: String
  val method: http.Method
  val data: Option[Any] = Option.empty
  val tls: Boolean = true
}