package com.maverick.musicwiki.models.artistModel

data class Artist(
    val image: List<Image>,
    val mbid: String,
    val name: String,
    val streamable: String,
    val url: String
)