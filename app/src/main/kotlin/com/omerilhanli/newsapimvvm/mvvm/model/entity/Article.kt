package com.omerilhanli.newsapimvvm.mvvm.model.entity

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(
    "source",
    "author",
    "title",
    "description",
    "url",
    "urlToImage",
    "publishedAt",
    "content"
)
data class Article(
    @JsonProperty("source") var source: Source? = null,
    @JsonProperty("author") var author: String? = null,
    @JsonProperty("title") var title: String? = null,
    @JsonProperty("description") var description: String? = null,
    @JsonProperty("url") var url: String? = null,
    @JsonProperty("urlToImage") var urlToImage: String? = null,
    @JsonProperty("publishedAt") var publishedAt: String? = null,
    @JsonProperty("content") var content: String? = null
)