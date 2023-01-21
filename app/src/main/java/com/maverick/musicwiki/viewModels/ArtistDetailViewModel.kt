package com.maverick.musicwiki.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maverick.musicwiki.api.ApiInterface
import com.maverick.musicwiki.api.RetrofitObject
import com.maverick.musicwiki.models.artistInfo.ArtistInfo
import com.maverick.musicwiki.models.topArtistAlbum.ArtistTopAlbumModel
import com.maverick.musicwiki.models.topArtistTracks.ArtistTopTrackModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.pow

class ArtistDetailViewModel : ViewModel() {
    var artistName = MutableLiveData<String>()
    var artistDetail = MutableLiveData<ArtistInfo>()
    var artistTopAlbum = MutableLiveData<ArtistTopAlbumModel>()
    var artistTopTrack = MutableLiveData<ArtistTopTrackModel>()

    fun getArtistDetail(artist: String): MutableLiveData<ArtistInfo> {
        val apiInterface = RetrofitObject.getInstance().create(ApiInterface::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val result = apiInterface.getArtistInfo(artist = artist)
            if (result.body() != null) {
                artistDetail.postValue(result.body())
                Log.d("artistDetail", artistDetail.value.toString())
            } else {
                Log.d("failed artistDetail", "nulls")
            }
        }
        return artistDetail
    }

    fun getArtistTopAlbum(artist: String): MutableLiveData<ArtistTopAlbumModel> {
        val apiInterface = RetrofitObject.getInstance().create(ApiInterface::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val result = apiInterface.getTopArtistAlbum(artist)
            if (result.body() != null) {
                artistTopAlbum.postValue(result.body())
                Log.d("artistTopAlbum", artistTopAlbum.value.toString())
            } else {
                Log.d("failed artistTopAlbum", "nulls")
            }
        }
        return artistTopAlbum
    }

    fun getArtistTopTrack(artist: String): MutableLiveData<ArtistTopTrackModel> {
        val apiInterface = RetrofitObject.getInstance().create(ApiInterface::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val result = apiInterface.getTopArtistTrack(artist)
            if (result.body() != null) {
                artistTopTrack.postValue(result.body())
                Log.d("artistTopTrack", artistTopTrack.value.toString())
            } else {
                Log.d("failed artistTopTrack", "nulls")
            }
        }
        return artistTopTrack
    }

    fun countViews(count: Long): String {
        val array = arrayOf(' ', 'k', 'M', 'B', 'T', 'P', 'E')
        val value = floor(log10(count.toDouble())).toInt()
        val base = value / 3
        if (value >= 3 && base < array.size) {
            return DecimalFormat("#0.0").format(count / 10.0.pow((base * 3).toDouble())) + array[base]
        } else {
            return DecimalFormat("#,##0").format(count)
        }
    }

    fun setArtistName(name: String) {
        artistName.value = name
    }

}