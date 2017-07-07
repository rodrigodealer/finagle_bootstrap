package com.github.rodrigodealer.model

import org.scalatest.{FlatSpec, Matchers}

class BasicFacebookUserTest extends FlatSpec with Matchers {

  "BasicFacebookUser" should "convert to json" in {
    val basicFacebookUser = BasicFacebookUser("1", "2")

    val json = basicFacebookUser.toJson
    json shouldBe "{\"name\":\"1\",\"id\":\"2\"}"
  }

}
