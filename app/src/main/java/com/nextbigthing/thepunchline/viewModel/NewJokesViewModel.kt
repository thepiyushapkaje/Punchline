package com.nextbigthing.thepunchline.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nextbigthing.thepunchline.api.NewApiService
import com.nextbigthing.thepunchline.data.DoctorJokes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewJokesViewModel @Inject constructor(private val newApiService: NewApiService) : ViewModel() {
    private val _doctorJokesResponse = MutableLiveData<DoctorJokes>()
    var doctorJokesResponse: LiveData<DoctorJokes> = _doctorJokesResponse

    fun fetchDoctorJokes(category:String) {
        viewModelScope.launch {
            try {
                val jokes = newApiService.getDoctorJokes(category)
                _doctorJokesResponse.value = jokes
            } catch (e: Exception) {
                Log.e("NewJokesViewModel", "Exception occurred: ${e.message}")
            }
        }
    }
}