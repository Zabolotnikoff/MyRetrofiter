package com.example.myretrofiter.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.home.models.*
import com.example.myretrofiter.Session
import com.example.myretrofiter.network.AuthorizedAPI
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class AuthorizedRepository {

    private var authorizedAPI: AuthorizedAPI? = null
    private var retrofit: Retrofit

    init {
        retrofit = retrofitInit()
        authorizedAPI = retrofit.create(AuthorizedAPI::class.java)
    }


    fun createNewLoan(loanRequest: LoanRequest): LiveData<Loan> {
        val result = MutableLiveData<Loan>()
        authorizedAPI?.createNewLoan(loanRequest)?.enqueue(object : Callback<Loan> {
            override fun onFailure(call: Call<Loan>, t: Throwable) {
                result.value = null
            }

            override fun onResponse(call: Call<Loan>, response: Response<Loan>) {
                when (response.code()) {
                    200 -> result.value = response.body()
                    400 -> result.value = null
                    403 -> result.value = null
                    else -> result.value = null
                }
            }
        })
        return result
    }

    fun getLoanData(id: Long): LiveData<Loan> {
        val result = MutableLiveData<Loan>()
        authorizedAPI?.getLoanData(id)?.enqueue(object : Callback<Loan> {
            override fun onFailure(call: Call<Loan>, t: Throwable) {
                result.value = null
            }

            override fun onResponse(call: Call<Loan>, response: Response<Loan>) {
                when (response.code()) {
                    200 -> result.value = response.body()
                    403 -> result.value = null
                    else -> result.value = null
                }
            }
        })
        return result
    }

    fun getLoansList(): LiveData<List<Loan>> {
        val result = MutableLiveData<List<Loan>>()

        authorizedAPI?.getLoansList()?.enqueue(object : Callback<List<Loan>> {
            override fun onFailure(call: Call<List<Loan>>, t: Throwable) {
                result.value = null
            }

            override fun onResponse(call: Call<List<Loan>>, response: Response<List<Loan>>) {
                when (response.code()) {
                    200 -> result.value = response.body()
                    403 -> result.value = null
                    else -> result.value = null
                }
            }
        })
        return result
    }

    fun getLoanConditions():LiveData<LoanConditions> {
        val result = MutableLiveData<LoanConditions>()

        authorizedAPI?.getLoanConditions()?.enqueue(object : Callback<LoanConditions> {
            override fun onFailure(call: Call<LoanConditions>, t: Throwable) {
                result.value = null
            }

            override fun onResponse(
                call: Call<LoanConditions>,
                response: Response<LoanConditions>
            ) {
                when (response.code()) {
                    200 -> result.value = response.body()
                    403 -> result.value = null
                    else -> result.value = null
                }
            }
        })
        return result
    }


    private fun retrofitInit(): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                chain.proceed(
                    chain
                        .request()
                        .newBuilder()
                        .header("Authorization", Session.token)
                        .build()
                )
            }
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
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
