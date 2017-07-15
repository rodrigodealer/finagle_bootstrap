package com.github.rodrigodealer

import com.twitter.finagle.http.{Request, Response, Status}
import com.twitter.finagle.{Service, http}
import com.twitter.util.Future
import org.apache.log4j.Logger

object SubscriptionService {

  private val log = Logger.getLogger(SubscriptionService.getClass)

  def apply(): Service[Request, Response] = {
    val verifyToken = "bla"
    (request: http.Request) => {
      request.getParam("hub.mode") match {
        case "subscribe" if request.getParam("hub.verify_token") == verifyToken =>
          log.info(s"Subscription accepted with hub.challenge: ${request.getParam("hub.challenge")}")
          val response = Responses.respond(request.getParam("hub.challenge"), Status.Ok)
          Future(response)
        case _ =>
          log.info(s"Subscription denied: Verify token: ${request.getParam("hub.verify_token")} hub.mode: ${request.getParam("hub.mode")}")
          val response = Responses.respond(Status.BadRequest)
          Future(response)
      }
    }
  }
}
