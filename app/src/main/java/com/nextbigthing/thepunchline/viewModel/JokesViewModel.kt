package com.nextbigthing.thepunchline.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nextbigthing.thepunchline.api.ApiService
import com.nextbigthing.thepunchline.data.Jokes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokesViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {
    private val _jokesResponse = MutableLiveData<Jokes>()
    var jokesResponse: LiveData<Jokes> = _jokesResponse

    fun fetchJokes(category: String, flags: String) {
        viewModelScope.launch {
            try {
                val jokes = apiService.getJokesFromApi(category, flags)
                _jokesResponse.value = jokes
            } catch (e: Exception) {
                Log.e("JokesViewModel", "Exception occurred: ${e.message}")
            }
        }
    }
}