package com.maverick.musicwiki.models.trackModel

data class Track(
    val artist: Artist,
    val duration: String,
    val image: List<Image>,
    val mbid: String,
    val name: String,
    val url: String
)