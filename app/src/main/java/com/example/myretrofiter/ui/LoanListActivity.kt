package com.example.myretrofiter.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
//import android.os.PersistableBundle
//import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.data.home.models.Loan
import com.example.myretrofiter.R
import com.example.myretrofiter.viewmodel.LoanViewModel
import kotlinx.android.synthetic.main.loan_list_activity.*

class LoanListActivity : AppCompatActivity() {

    private lateinit var loanViewModel: LoanViewModel
//    private lateinit var loanAdapter: LoanListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loan_list_activity)

        var loanAdapter = LoanListAdapter()

        reciclerView.adapter = loanAdapter
        reciclerView.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)


        loanViewModel = ViewModelProvider(this)[LoanViewModel::class.java]
        loanViewModel.getLoansList()

        loanViewModel.loanListLiveData?.observe(this,
            Observer {
                if (it != null) {
                    //                   recyclerViewHome.visibility = View.VISIBLE
                    loanAdapter.setLoanList(it as ArrayList<Loan>)
                } else {
                    showToast("Что-то пошло нетак :(")
                }
//                progress_home.visibility = View.GONE
            })




        loanAdapter.setLoanListener(object : LoanListAdapter.LoanListener {
            override fun onLoanClick(loan: Loan) {
                val intent = Intent(this@LoanListActivity, DetailLoanActivity::class.java)
                intent.putExtra("id", loan.id)
                startActivity(intent)
            }
        })

    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

}