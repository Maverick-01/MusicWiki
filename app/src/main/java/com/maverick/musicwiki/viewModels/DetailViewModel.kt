package com.maverick.musicwiki.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maverick.musicwiki.api.ApiInterface
import com.maverick.musicwiki.api.RetrofitObject
import com.maverick.musicwiki.models.albumModel.AlbumModel
import com.maverick.musicwiki.models.albumModel.Artist
import com.maverick.musicwiki.models.artistModel.ArtistModel
import com.maverick.musicwiki.models.tagInfoModel.TagInfoModel
import com.maverick.musicwiki.models.trackModel.TrackModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {
    var result = MutableLiveData<TagInfoModel>()
    var tagName = MutableLiveData<String>()
    var topAlbum = MutableLiveData<AlbumModel>()
    var topArtist = MutableLiveData<ArtistModel>()
    var topTrack = MutableLiveData<TrackModel>()
    fun getData(albumName: String): MutableLiveData<TagInfoModel> {
        val apiInterface = RetrofitObject.getInstance().create(ApiInterface::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            result.postValue(apiInterface.getTagInfo(albumName).body())
            if (result.value != null) {
                Log.d("tagInfo", result.value.toString())
            } else {
                Log.d("failed tagInfo", "nulls")
            }
        }
        return result
    }

    fun getTopAlbum(tagName: String): MutableLiveData<AlbumModel> {
        val apiInterface = RetrofitObject.getInstance().create(ApiInterface::class.java)
        CoroutineScope(Dispatchers.Main).launch {
            val result = apiInterface.getTopAlbum(tagName)
            if (result.body() != null) {
                topAlbum.postValue(result.body())
                Log.d("album", topAlbum.value.toString())
            } else {
                Log.d("failed album", "nulls")
            }
        }
        return topAlbum
    }

    fun getTopArtist(tagName: String): MutableLiveData<ArtistModel> {
        val apiInterface = RetrofitObject.getInstance().create(ApiInterface::class.java)
        CoroutineScope(Dispatchers.Main).launch {
            val result = apiInterface.getTopArtist(tagName)
            if (result.body() != null) {
                topArtist.postValue(result.body())
                Log.d("artist", topArtist.value.toString())
            } else {
                Log.d("failed artist", "nulls")
            }
        }
        return topArtist
    }

    fun getTopTrack(tagName: String): MutableLiveData<TrackModel> {
        val apiInterface = RetrofitObject.getInstance().create(ApiInterface::class.java)
        CoroutineScope(Dispatchers.Main).launch {
            val result = apiInterface.getTopTrack(tagName)
            if (result.body() != null) {
                topTrack.postValue(result.body())
                Log.d("track", topTrack.value.toString())
            } else {
                Log.d("failed track", "nulls")
            }
        }
        return topTrack
    }

    fun setTagName(tag: String) {
        tagName.value = tag
    }

    fun getTagName(): String {
        return tagName.value.toString()
    }
}