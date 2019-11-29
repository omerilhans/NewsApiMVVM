package com.omerilhanli.newsapimvvm.mvvm.model.entity

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("id", "name")
data class Source(
    @JsonProperty("id") var id: String?=null,
    @JsonProperty("name") var name: String?=null
)