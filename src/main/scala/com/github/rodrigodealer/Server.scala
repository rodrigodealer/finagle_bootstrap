package com.github.rodrigodealer

import java.util.concurrent.TimeUnit._

import com.twitter.finagle.Http
import com.twitter.finagle.http.filter.ExceptionFilter
import com.twitter.finagle.service.TimeoutFilter
import com.twitter.finagle.util.DefaultTimer._
import com.twitter.server.TwitterServer
import com.twitter.util.{Await, Duration}


object Server extends TwitterServer {
  def main = new FinagleServer
}

class FinagleServer extends TwitterServer {
  val service = new TimeoutFilter(Duration(5, SECONDS), twitter) andThen
    ExceptionFilter andThen
    Router()

  val server = Http.server.withLabel("finagle")
    .withStatsReceiver(statsReceiver)
    .withHttpStats
    .serve(":8080", service)
  Await.ready(server)
  Await.ready(adminHttpServer)

  onExit { server.close() }
}