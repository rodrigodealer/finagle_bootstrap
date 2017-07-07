package com.github.rodrigodealer.json

import com.github.rodrigodealer.model.HealthcheckItem
import org.scalatest.{FlatSpec, Matchers}

class JsonUtilTest extends FlatSpec with Matchers {
  "List" should "convert to json" in {
    val mylist = List("1", "2")

    val json = JsonUtil.toJson(mylist)
    json shouldBe "[\"1\",\"2\"]"
  }

  "Json" should "convert to Class" in {
    val json = "{\"name\": \"facebook\",\"severity\": \"severe\", \"status\": true }"

    val klazz = JsonUtil.fromJson[HealthcheckItem](json)

    klazz.isInstanceOf[HealthcheckItem] shouldBe true
  }
}
