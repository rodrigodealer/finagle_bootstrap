package com.github.rodrigodealer

import com.twitter.finagle.Service
import com.twitter.finagle.http.{Request, Response}
import com.twitter.util.Future


object UserService {
  def apply(username: String): Service[Request, Response] = {
    new Service[Request, Response] {
      override def apply(request: Request): Future[Response] = {
        val response = Response()
        response.setContentString(username)
        Future.value(response)
      }
    }
  }
}