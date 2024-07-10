package com.imrkjoseph.squadzipexam.contacts.data.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ContactDetailsResponse(
    @JsonProperty("data")
    val data: Data? = null,

    @JsonProperty("support")
    val support: Support? = null
)