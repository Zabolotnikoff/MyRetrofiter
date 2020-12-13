package com.example.myapplication.data.home.models

import java.io.Serializable

data class Loan(
    var amount: Int? = 0,
    var date: String? = "",  //($date-time)
    var firstName: String? = "",
    var id: Long? = 0,
    var lastName: String? = "",
    var percent: Double? = 0.0,
    var period: Int? = 0,
    var phoneNumber: String? = "",
    var state: String? = ""
): Serializable