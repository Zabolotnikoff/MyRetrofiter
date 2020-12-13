package com.example.myretrofiter.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.home.models.Auth
import com.example.myapplication.data.home.models.UserEntity
import com.example.myretrofiter.Session
import com.example.myretrofiter.network.UnauthorizedAPI
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class UnauthorizedRepository {

    private var offlineAPI: UnauthorizedAPI? = null
    private var retrofit: Retrofit

    init {
        retrofit = retrofitInit()
        offlineAPI = retrofit.create(UnauthorizedAPI::class.java)
    }

    fun loginIntoApp(authData: Auth): LiveData<String> {
        val result = MutableLiveData<String>()

        offlineAPI?.loginIntoApp(authData)?.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                result.value = null
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                when (response.code()) {
                    200 -> result.value = response.body()
                    404 -> result.value = null
                    else -> result.value = null
                }
            }
        })
        return result
    }

    fun registerInApp(authData: Auth): LiveData<Int> {
        val result = MutableLiveData<Int>()

        offlineAPI?.registerInApp(authData)?.enqueue(object : Callback<UserEntity> {
            override fun onFailure(call: Call<UserEntity>, t: Throwable) {
                result.value = null
            }

            override fun onResponse(call: Call<UserEntity>, response: Response<UserEntity>) {
                result.value = response.code()
            }
        })
        return result
    }

    private fun retrofitInit(): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(2, TimeUnit.SECONDS)
            .connectTimeout(2, TimeUnit.SECONDS)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(Session.BASEURL)
            .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit
    }
}