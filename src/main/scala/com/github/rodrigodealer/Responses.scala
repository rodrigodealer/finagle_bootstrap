package com.github.rodrigodealer

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.twitter.finagle.http.{Response, Status}
import com.twitter.io.Buf
import org.jboss.netty.buffer.ChannelBuffers.copiedBuffer


object Responses {
  val mapper = new ObjectMapper()
  mapper.registerModule(DefaultScalaModule)

  def json(data: Any, code: Status) = {
    val response = Response()
    response.setContentTypeJson
    response.status = code
    response.content = Buf.Utf8(mapper.writeValueAsString(data))
    response
  }

  def respond(data: String, code: Status) = {
    val response = Response()
    response.content = Buf.Utf8(data)
    response
  }

  def respond(code: Status) = {
    val response = Response()
    response.status = code
    response
  }
}
