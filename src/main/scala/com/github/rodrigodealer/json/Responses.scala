package com.github.rodrigodealer.json

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.twitter.finagle.http.{Response, Status}
import com.twitter.finagle.netty3.ChannelBufferBuf
import org.jboss.netty.buffer.ChannelBuffers.copiedBuffer


object Responses {
  val mapper = new ObjectMapper()
  mapper.registerModule(DefaultScalaModule)

  def json(data: Any, code: Status, gzip: Boolean = false) = {
    val response = Response()
    response.setContentTypeJson
    response.content = new ChannelBufferBuf(copiedBuffer(mapper.writeValueAsBytes(data)))
    response
  }
}
