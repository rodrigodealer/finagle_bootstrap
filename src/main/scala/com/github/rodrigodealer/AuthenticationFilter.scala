package com.github.rodrigodealer

import com.twitter.finagle.http.{Status, Request => FReq, Response => FRep}
import com.twitter.finagle.{Service, SimpleFilter}
import com.twitter.util.Future

class AuthenticationFilter extends SimpleFilter[FReq, FRep] {

  val WHITELISTED_PATHS = List("/healthcheck", "/auth")
  def apply(req: FReq, service: Service[FReq, FRep]): Future[FRep] = {

    WHITELISTED_PATHS.exists(p => p.equals(req.path)) match {
      case false => containsHeader(req) (service(req)) {
        Future(Responses.respond(Status.Unauthorized))
      }
      case true => service(req)
    }

  }

  def containsHeader(req: FReq) (block: => Future[FRep]) (errorBlock: => Future[FRep]): Future[FRep] = {
    req.headerMap.contains("X-Authentication") match {
      case false => errorBlock
      case true => block
    }
  }
}

