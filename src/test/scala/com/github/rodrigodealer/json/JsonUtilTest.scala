package com.github.rodrigodealer.json

import org.scalatest.{FlatSpec, Matchers}

class JsonUtilTest extends FlatSpec with Matchers {
  "List" should "convert to json" in {
    val mylist = List("1", "2")

    val json = JsonUtil.toJson(mylist)
    json shouldBe "[\"1\",\"2\"]"
  }
}
