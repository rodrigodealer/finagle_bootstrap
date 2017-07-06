package com.github.rodrigodealer.json

import com.fasterxml.jackson.databind.ObjectMapper
import com.twitter.finagle.http.Response
import com.twitter.finagle.netty3.ChannelBufferBuf
import com.twitter.io.Buf.ByteBuffer.Owned
import com.twitter.util.Future
import org.jboss.netty.buffer.ChannelBuffer
import org.jboss.netty.handler.codec.http.{DefaultHttpResponse, HttpRequest, HttpResponse, HttpResponseStatus}
import org.jboss.netty.handler.codec.http.HttpVersion.HTTP_1_1
import org.jboss.netty.handler.codec.http.HttpResponseStatus.{INTERNAL_SERVER_ERROR, NOT_FOUND}
import org.jboss.netty.util.CharsetUtil.UTF_8
import org.jboss.netty.buffer.ChannelBuffers.copiedBuffer


object Responses {

  // Used to convert objects into json
  val mapper = new ObjectMapper

  def respond(value: Any) = {
    val response = Response()
    response.setContentTypeJson()
    val buf = new ChannelBufferBuf(copiedBuffer(mapper.writeValueAsBytes(value)))
    response.content = buf
    Future.value(response)
  }

  // Create an HttpResponse from a status and some content.
//  private def respond(status: HttpResponseStatus, content: ChannelBuffer): HttpResponse = {
//    val response = new DefaultHttpResponse(HTTP_1_1, status)
//    response.setContent(content)
//    response
//  }
//
//  object OK {
//    def apply(req: HttpRequest, service: (HttpRequest) => Object): HttpResponse =
//      respond(HttpResponseStatus.OK,
//        copiedBuffer(mapper.writeValueAsBytes(service(req))))
//  }
//
//  object NotFound {
//    def apply(): HttpResponse  =
//      respond(NOT_FOUND,
//        copiedBuffer("{\"status\":\"NOT_FOUND\"}", UTF_8))
//  }
//
//  object InternalServerError {
//    def apply(message: String): HttpResponse =
//      respond(INTERNAL_SERVER_ERROR,
//        copiedBuffer("{\"status\":\"INTERNAL_SERVER_ERROR\", " +
//          "\"message\":\"" + message + "\"}", UTF_8))
//  }
}
