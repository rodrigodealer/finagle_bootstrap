package com.github.rodrigodealer

import com.github.rodrigodealer.model.HealthcheckStatus.{healthcheckMessage, healthcheckStatusCode}
import com.github.rodrigodealer.model.{HealthcheckItem, HealthcheckStatus}
import com.twitter.finagle.http.{Request, Response}
import com.twitter.finagle.{Service, http}
import com.twitter.util.Future


object HealthcheckService {

  def apply(): Service[Request, Response] = {
    (request: http.Request) => {
      val facebookToken = FacebookService.getToken map {
        case Some(token) => HealthcheckItem("facebook", "severe", true)
        case _ => HealthcheckItem("facebook", "severe", false)
      }

      (for {
        facebookCheck <- facebookToken
        facebook2Check <- facebookToken
      } yield List(facebookCheck, facebook2Check)) flatMap { statusList =>
        val itemsStatus = statusList.forall(_.status)
        val message = healthcheckMessage(itemsStatus)
        Future(Responses.json(HealthcheckStatus(message, statusList), healthcheckStatusCode(itemsStatus)))
      }
    }
  }
}




