package com.github.rodrigodealer

import com.github.rodrigodealer.json.JsonUtil
import com.github.rodrigodealer.model.{BasicFacebookUser, FacebookUpdate}
import com.twitter.finagle.http.{Request, Response, Status}
import com.twitter.finagle.{Service, http}
import com.twitter.util.Future
import org.apache.log4j.Logger

object UserUpdateService {

  private val log = Logger.getLogger(SubscriptionService.getClass)

  def apply(facebookService: FacebookService) : Service[Request, Response] = {
    (request: http.Request) => {
      val update = JsonUtil.fromJson[FacebookUpdate](request.contentString)
      log.info(s"Got updates for ${update.entry.size} entries")
      val user = BasicFacebookUser("12312313", "12312313")
      update.entry.foreach { entry =>
        log.info(s"Getting information for ${entry.uid}")
        facebookService.facebookCheck(entry.uid) flatMap {
          case Some(u) =>
            Future("bla")
          case None =>
            log.error("Didn't find the user")
            throw new IllegalStateException()
        }
      }
      Future(Responses.respond(Status.Ok))
    }
  }
}
