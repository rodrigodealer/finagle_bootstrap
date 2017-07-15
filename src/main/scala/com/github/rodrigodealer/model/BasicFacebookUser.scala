package com.github.rodrigodealer.model

import com.github.rodrigodealer.json.JsonModel

case class FacebookToken(access_token: String, token_type: String)

case class BasicFacebookUser(name: String,
                             id: String) extends JsonModel