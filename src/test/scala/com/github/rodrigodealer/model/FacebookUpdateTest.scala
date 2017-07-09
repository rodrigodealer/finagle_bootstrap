package com.github.rodrigodealer.model

import com.github.rodrigodealer.json.JsonUtil
import org.scalatest.{FlatSpec, Matchers}

class FacebookUpdateTest extends FlatSpec with Matchers {

  val json = """{"object":"user","entry":[{"uid":"123456789","id":"123456789","time":1232313,"changed_fields":["friends"]}]}"""

  "FacebookUpdate" should "parse to json" in {
    val facebookUpdateEntries = List(FacebookUpdateEntry("123456789", "123456789", 1232313.toLong, List("friends")))
    val facebookUpdate = FacebookUpdate("user", facebookUpdateEntries)

    facebookUpdate.toJson shouldBe json
  }

  "FacebookUpdate" should "from json" in {
    val klazz = JsonUtil.fromJson[FacebookUpdate](json)

    klazz.`object` shouldBe "user"
    klazz.entry.size shouldBe 1
    klazz.entry.head.id shouldBe "123456789"
    klazz.entry.head.uid shouldBe "123456789"
  }
}
