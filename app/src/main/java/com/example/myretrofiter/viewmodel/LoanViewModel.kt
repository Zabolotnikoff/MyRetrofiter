package com.example.myretrofiter.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.home.models.Auth
import com.example.myapplication.data.home.models.Loan
import com.example.myapplication.data.home.models.LoanConditions
import com.example.myapplication.data.home.models.LoanRequest
import com.example.myretrofiter.data.AuthorizedRepository
import com.example.myretrofiter.data.UnauthorizedRepository

class LoanViewModel(application: Application) : AndroidViewModel(application) {

    private val authRepository = AuthorizedRepository()
    private val unauthRepository = UnauthorizedRepository()
    var stringLiveData: LiveData<String>? = null
    var intLiveData: LiveData<Int>? = null
    var loanLiveDataCreate: LiveData<Loan>? = null
    var loanLiveDataGet: LiveData<Loan>? = null
    var loanListLiveData: LiveData<List<Loan>>? = null
    var loanConditionsListLiveData: LiveData<LoanConditions>? = null

    init {
        stringLiveData = MutableLiveData()
        intLiveData = MutableLiveData()
        loanLiveDataCreate = MutableLiveData()
        loanLiveDataGet = MutableLiveData()
        loanListLiveData = MutableLiveData()
        loanConditionsListLiveData = MutableLiveData()
    }

    fun loginIntoApp(authData: Auth) {
        stringLiveData = unauthRepository.loginIntoApp(authData)
    }

    fun registerInApp(authData: Auth) {
        intLiveData = unauthRepository.registerInApp(authData)
    }


    fun createNewLoan(loanRequest: LoanRequest) {
        loanLiveDataCreate = authRepository.createNewLoan(loanRequest)
    }

    fun getLoanData(id: Long?) {
        loanLiveDataGet = id?.let { authRepository.getLoanData(it) }
    }

    fun getLoansList() {
        loanListLiveData = authRepository.getLoansList()
    }

    fun getLoanConditions() {
        loanConditionsListLiveData = authRepository.getLoanConditions()
    }

}
