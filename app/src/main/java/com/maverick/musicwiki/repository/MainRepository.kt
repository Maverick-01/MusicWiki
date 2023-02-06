package com.maverick.musicwiki.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.maverick.musicwiki.api.ApiInterface
import com.maverick.musicwiki.models.tagModel.TagModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainRepository(private val apiInterface: ApiInterface) {
    private var getTopTagsObservable = MutableLiveData<TagModel>()
    fun getTopTags(): LiveData<TagModel> {
        CoroutineScope(Dispatchers.Main).launch {
            getTopTagsObservable.postValue(apiInterface.getTopTags().body())
            if (getTopTagsObservable.value != null) {
                Log.d("tags", getTopTagsObservable.value.toString())
            } else {
                Log.d("failed tags", "nulls")
            }
        }
        return getTopTagsObservable
    }
}