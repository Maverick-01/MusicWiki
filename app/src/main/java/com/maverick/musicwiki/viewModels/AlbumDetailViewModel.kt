package com.maverick.musicwiki.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maverick.musicwiki.api.ApiInterface
import com.maverick.musicwiki.api.RetrofitObject
import com.maverick.musicwiki.models.albumDetail.AlbumDetail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlbumDetailViewModel : ViewModel() {
    var albumDetail = MutableLiveData<AlbumDetail>()
    fun getAlbumDetail(artist: String, album: String): MutableLiveData<AlbumDetail> {
        val apiInterface = RetrofitObject.getInstance().create(ApiInterface::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val result = apiInterface.getAlbumDetail(artist = artist, album = album)
            if (result.body() != null) {
                albumDetail.postValue(result.body())
                Log.d("albumDetail", albumDetail.value.toString())
            } else {
                Log.d("failed albumDetail", "nulls")
            }
        }
        return albumDetail
    }
}