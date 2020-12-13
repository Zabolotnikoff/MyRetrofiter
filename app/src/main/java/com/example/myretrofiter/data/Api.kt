package com.example.myretrofiter.network

import com.example.myapplication.data.home.models.*
import retrofit2.Call
import retrofit2.http.*


interface UnauthorizedAPI {
    @POST("login")
    fun loginIntoApp(@Body authData: Auth): Call<String>

    @POST("registration")
    fun registerInApp(@Body authData: Auth): Call<UserEntity>
}

interface AuthorizedAPI {
    @POST("loans")
    fun createNewLoan(@Body loanRequestData: LoanRequest): Call<Loan>

    @GET("loans/{id}/")
    fun getLoanData(@Path("id") id: Long): Call<Loan>

    @GET("loans/all")
    fun getLoansList(): Call<List<Loan>>

    @GET("loans/conditions")
    fun getLoanConditions(): Call<LoanConditions>
}