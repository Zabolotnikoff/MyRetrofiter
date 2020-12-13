package com.example.myapplication.data.home.models

data class LoanRequest(
    var amount: Int? = 0,
    var firstName: String? = "",
    var lastName: String? = "",
    var percent: Double? = 0.0,
    var period: Int? = 0,
    var phoneNumber: String = ""
)
