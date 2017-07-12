package com.github.rodrigodealer

import com.twitter.finagle.Service
import com.twitter.finagle.http.Method.{Get, Post}
import com.twitter.finagle.http.path.{->, /, Root}
import com.twitter.finagle.http.service.RoutingService
import com.twitter.finagle.http.{Request, Response}

object Router {

  val facebookService = new FacebookService

  def apply() : Service[Request, Response] = {
    RoutingService.byMethodAndPathObject {
      case Get -> Root / "healthcheck" => HealthcheckService(facebookService)
      case Get -> Root / "user" / user => facebookService(user)
      case Post -> Root / "subscription" => SubscriptionService()
    }
  }
}
