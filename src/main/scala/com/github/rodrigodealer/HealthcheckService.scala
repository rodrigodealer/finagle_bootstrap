package com.github.rodrigodealer

import com.github.rodrigodealer.json.Responses
import com.twitter.finagle.http.{Request, Response, Status}
import com.twitter.finagle.{Service, http}
import com.twitter.util.Future


object HealthcheckService {

  def apply(): Service[Request, Response] = {
    (request: http.Request) => {
      val item = HealthcheckItem("facebook", "severe", true)
      val status = HealthcheckStatus("WORKING", List(item))
      val response = Responses.json(status, Status.Ok)
      Future.value(response)
    }
  }
}


case class HealthcheckStatus(message: String, services: List[HealthcheckItem])

case class HealthcheckItem(name: String, severity: String, status: Boolean)