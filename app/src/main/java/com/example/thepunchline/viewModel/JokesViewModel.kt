package com.example.thepunchline.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thepunchline.api.ApiService
import com.example.thepunchline.data.Jokes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class JokesViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {
    private val _jokesResponse = MutableLiveData<Jokes>()
    var jokesResponse: LiveData<Jokes> = _jokesResponse

    fun fetchJokes(category: String, flags: String) {
        CoroutineScope(Dispatchers.IO).launch {
            apiService.getJokesFromApi(category, flags).enqueue(object : Callback<Jokes> {
                override fun onResponse(call: Call<Jokes>, response: Response<Jokes>) {
                    _jokesResponse.value = response.body()
                }

                override fun onFailure(call: Call<Jokes>, t: Throwable) {
                    Log.d("TAG", "onResponse: ${t.message}")
                }
            })
        }
    }
}