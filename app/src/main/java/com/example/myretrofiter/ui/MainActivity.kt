package com.example.myretrofiter.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.data.home.models.Auth
import com.example.myretrofiter.R
import com.example.myretrofiter.Session
import com.example.myretrofiter.viewmodel.LoanViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var loanViewModel: LoanViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonLogin.setOnClickListener {
            loanViewModel = ViewModelProvider(this)[LoanViewModel::class.java]
            val authData = Auth(editTextLogin.text.toString(), editTextPassword.text.toString())

            register(authData)
        }


    }

    fun register(authData: Auth) {
        loanViewModel.registerInApp(authData)
        loanViewModel.intLiveData?.observe(this,
            Observer {
                if (it == 200 || it == 400) {
                    login(authData)
                } else {
                    showToast("Что-то во время регистрации пошло нетак :(")
                }
            })

    }

    fun login(authData: Auth) {
        loanViewModel.loginIntoApp(authData)
        loanViewModel.stringLiveData?.observe(this,
            Observer {
                if (it != null) {
                    Session.token = it.toString()
                    startActivity(Intent(this, LoanListActivity::class.java))
                } else {
                    showToast("Что-то при авторизации пошло нетак :(")
                }
            })

    }


    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

}
