package com.github.rodrigodealer

import java.util.concurrent.TimeUnit._

import com.twitter.finagle.Http
import com.twitter.finagle.http.filter.ExceptionFilter
import com.twitter.finagle.service.TimeoutFilter
import com.twitter.finagle.util.DefaultTimer._
import com.twitter.util.{Await, Duration}


object Server extends App {
  val service = new TimeoutFilter(Duration(5, SECONDS), twitter) andThen
    ExceptionFilter andThen
    Router()

  val server = Http.server.withLabel("server").withHttpStats
    .serve(":8080", service)
  Await.ready(server)
}