package com.github.rodrigodealer.model

import com.twitter.finagle.http.Status.{InternalServerError, Ok}

case class HealthcheckStatus(message: String, services: List[HealthcheckItem])

case class HealthcheckItem(name: String, severity: String, status: Boolean)

object HealthcheckStatus {
  def healthcheckMessage(status: Boolean) = status match {
    case true => "WORKING"
    case _ => "FAILED"
  }

  def healthcheckStatusCode(status: Boolean) = status match {
    case true => Ok
    case _ => InternalServerError
  }
}