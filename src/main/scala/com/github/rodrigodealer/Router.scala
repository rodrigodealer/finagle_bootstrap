package com.github.rodrigodealer

import com.twitter.finagle.Service
import com.twitter.finagle.http.Method.Get
import com.twitter.finagle.http.path.{->, /, Root}
import com.twitter.finagle.http.service.RoutingService
import com.twitter.finagle.http.{Request, Response}

object Router {
  def apply() : Service[Request, Response] = {
    RoutingService.byMethodAndPathObject {
      case Get -> Root / "healthcheck" => HealthcheckService()
      case Get -> Root / "user" / user => FacebookService(user)
    }
  }
}
