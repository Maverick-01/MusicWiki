package com.maverick.musicwiki.api

import com.maverick.musicwiki.api.ApiUtility.Companion.API_KEY
import com.maverick.musicwiki.models.albumDetail.AlbumDetail
import com.maverick.musicwiki.models.albumModel.AlbumModel
import com.maverick.musicwiki.models.artistInfo.ArtistInfo
import com.maverick.musicwiki.models.artistModel.ArtistModel
import com.maverick.musicwiki.models.tagInfoModel.TagInfoModel
import com.maverick.musicwiki.models.tagModel.TagModel
import com.maverick.musicwiki.models.topArtistAlbum.ArtistTopAlbumModel
import com.maverick.musicwiki.models.topArtistTracks.ArtistTopTrackModel
import com.maverick.musicwiki.models.trackModel.TrackModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("?method=tag.getTopTags")
    suspend fun getTopTags(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("format") format: String = "json"
    ): Response<TagModel>

    @GET("?method=tag.gettopalbums")
    suspend fun getTopAlbum(
        @Query("tag") tag: String,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("format") format: String = "json"
    ): Response<AlbumModel>

    @GET("?method=tag.gettopartists")
    suspend fun getTopArtist(
        @Query("tag") tag: String,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("format") format: String = "json"
    ): Response<ArtistModel>

    @GET("?method=tag.gettoptracks")
    suspend fun getTopTrack(
        @Query("tag") tag: String,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("format") format: String = "json"
    ): Response<TrackModel>

    @GET("?method=tag.getinfo")
    suspend fun getTagInfo(
        @Query("tag") tag: String,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("format") format: String = "json"
    ): Response<TagInfoModel>

    @GET("?method=album.getinfo")
    suspend fun getAlbumDetail(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("artist") artist: String,
        @Query("album") album: String,
        @Query("format") format: String = "json"
    ): Response<AlbumDetail>

    @GET("?method=artist.gettopalbums")
    suspend fun getTopArtistAlbum(
        @Query("artist") artist: String,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("format") format: String = "json"
    ): Response<ArtistTopAlbumModel>

    @GET("?method=artist.gettoptracks")
    suspend fun getTopArtistTrack(
        @Query("artist") artist: String,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("format") format: String = "json"
    ): Response<ArtistTopTrackModel>

    @GET("?method=artist.getinfo")
    suspend fun getArtistInfo(
        @Query("artist") artist: String,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("format") format: String = "json"
    ): Response<ArtistInfo>
}