package com.imrkjoseph.squadzipexam.contacts.data.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ContactListResponse(
    @JsonProperty("data")
    val data: List<Data> = emptyList(),

    @JsonProperty("page")
    val page: Int? = null,

    @JsonProperty("per_page")
    val perPage: Int? = null,

    @JsonProperty("support")
    val support: Support? = null,

    @JsonProperty("total")
    val total: Int? = null,

    @JsonProperty("total_pages")
    val totalPages: Int? = null
)

data class Data(
    @JsonProperty("avatar")
    val avatar: String? = null,

    @JsonProperty("email")
    val email: String? = null,

    @JsonProperty("first_name")
    val firstName: String? = null,

    @JsonProperty("id")
    val id: Int,

    @JsonProperty("last_name")
    val lastName: String? = null
)

data class Support(
    @JsonProperty("text")
    val text: String? = null,

    @JsonProperty("url")
    val url: String? = null
)