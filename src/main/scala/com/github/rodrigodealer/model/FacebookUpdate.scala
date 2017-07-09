package com.github.rodrigodealer.model

import com.github.rodrigodealer.json.JsonModel

case class FacebookUpdate(`object`: String, entry: List[FacebookUpdateEntry]) extends JsonModel

case class FacebookUpdateEntry(uid: String, id: String, time: Long, changed_fields: List[String])
