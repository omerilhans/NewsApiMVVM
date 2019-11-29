package com.omerilhanli.newsapimvvm.mvvm.model.entity

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("status", "totalResults", "articles")
data class News(
    @JsonProperty("status") var status: String?=null,
    @JsonProperty("totalResults") var totalResults: Int?=null,
    @JsonProperty("articles") var articles: List<Article>?=null
)