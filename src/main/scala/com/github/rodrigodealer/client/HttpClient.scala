package com.github.rodrigodealer.client

import com.twitter.finagle.{Http, Service, http}

trait HttpClient {
  def httpsClient(host: String) : Service[http.Request, http.Response] = {
    val service : Service[http.Request, http.Response] = Http
      .client
      .withTls(host)
      .newService(s"$host:443")
    service
  }
}
