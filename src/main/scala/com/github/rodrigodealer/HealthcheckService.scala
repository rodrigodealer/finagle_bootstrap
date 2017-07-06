package com.github.rodrigodealer

import com.sun.xml.internal.ws.client.sei.ResponseBuilder
import com.twitter.finagle.{Service, http}
import com.twitter.finagle.http.{Request, Response}
import com.twitter.util.Future


object HealthcheckService {

  def apply(): Service[Request, Response] = {
    new Service[Request, Response] {
      override def apply(request: http.Request): Future[Response] = {
        val response = Response()
        response.setContentTypeJson()
        response.content(null)
        Future.value(response)
      }
    }
  }
}
