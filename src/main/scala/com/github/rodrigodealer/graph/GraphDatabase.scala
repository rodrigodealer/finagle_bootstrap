package com.github.rodrigodealer.graph


import com.github.rodrigodealer.model.BasicFacebookUser
import com.outr.arango.managed.{Graph, VertexCollection}
import io.youi.net.URL

object GraphConfig {
  val url : URL = new URL(host = "localhost")
}

object GraphDatabase extends Graph("default", url = GraphConfig.url) {
  val basicFacebookUser : VertexCollection[BasicFacebookUser] = vertex[BasicFacebookUser]("basicFacebookUser")
}