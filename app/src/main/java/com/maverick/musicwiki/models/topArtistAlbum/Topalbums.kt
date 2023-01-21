package com.maverick.musicwiki.models.topArtistAlbum

import com.google.gson.annotations.SerializedName

data class Topalbums(
    @SerializedName("@attr")
    val attr: Attr,
    val album: List<Album>
)