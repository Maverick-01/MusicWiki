package com.maverick.musicwiki.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.maverick.musicwiki.models.tagModel.TagModel
import com.maverick.musicwiki.repository.MainRepository

class MainViewModel(repository: MainRepository) : ViewModel() {
    var result: LiveData<TagModel> = repository.getTopTags()
}