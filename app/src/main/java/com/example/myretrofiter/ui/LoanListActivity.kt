package com.example.myretrofiter.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
//import android.os.PersistableBundle
//import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.data.home.models.Loan
import com.example.myretrofiter.R
import com.example.myretrofiter.domain.LoanListAdapter
import com.example.myretrofiter.viewmodel.LoanViewModel
import kotlinx.android.synthetic.main.activity_loan_list.*

class LoanListActivity : AppCompatActivity() {

    private lateinit var loanViewModel: LoanViewModel
    //private var loanViewModel: LoanViewModel by viewModels ()

    var loanAdapter = LoanListAdapter { loan ->
        val intent = Intent(this@LoanListActivity, DetailLoanActivity::class.java)
        intent.putExtra("id", loan.id)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loan_list)

        reciclerView.adapter = loanAdapter
        reciclerView.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

        loanViewModel = ViewModelProvider(this).get(LoanViewModel::class.java)
        loanViewModel.getLoansList()

        loanViewModel.loanListLiveData?.observe(this,
            Observer { loanList ->
                if (loanList != null) {
                    //                   recyclerViewHome.visibility = View.VISIBLE
                    loanAdapter.setLoanList(loanList as? ArrayList<Loan>)
                } else {
                    showToast("Что-то пошло нетак :(")
                }
            })
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

}