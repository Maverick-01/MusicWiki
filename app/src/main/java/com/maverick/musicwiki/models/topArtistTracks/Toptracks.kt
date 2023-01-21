package com.maverick.musicwiki.models.topArtistTracks

import com.google.gson.annotations.SerializedName

data class Toptracks(
    @SerializedName("@attr")
    val attr: Attr,
    val track: List<Track>
)