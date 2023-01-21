package com.maverick.musicwiki.models.artistInfo

data class Bio(
    val content: String,
    val links: Links,
    val published: String,
    val summary: String
)