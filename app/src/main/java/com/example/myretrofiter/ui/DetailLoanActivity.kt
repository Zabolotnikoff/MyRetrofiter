package com.example.myretrofiter.ui

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.data.home.models.Loan
import com.example.myapplication.data.home.models.enums.LoanStates
import com.example.myretrofiter.R
import com.example.myretrofiter.viewmodel.LoanViewModel
import kotlinx.android.synthetic.main.activity_detail_loan.*
import kotlinx.android.synthetic.main.item_loan_list.view.*


class DetailLoanActivity : AppCompatActivity() {

    private lateinit var loanViewModel: LoanViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_loan)

        val parameters = intent.getSerializableExtra("id") as Long?

        loanViewModel = ViewModelProvider(this)[LoanViewModel::class.java]
        loanViewModel.getLoanData(parameters)

        loanViewModel.loanLiveDataGet?.observe(this,
            Observer { loan ->
                if (loan != null) {
                    bind(loan)
                } else {
                    showToast("Что-то пошло нетак :(")
                }
            })
    }

    private fun bind(item: Loan) {

        textViewId.text = item.id.toString()
        textViewAmount.text = item.amount.toString()
        textViewPeriod.text = item.period.toString()
        textViewPercent.text = item.percent.toString()
        textViewDate.text = item.date.toString().substring(0, 10)
        textViewName.text = item.firstName.toString()
        textViewphoneNumber.text = item.phoneNumber.toString()
        textViewState.text = item.state.toString()

        val stateRu: Pair<String, Int> = when (item.state) {
            LoanStates.APPROVED.toString() -> "Согласовано" to Color.GREEN
            LoanStates.REGISTERED.toString() -> "На рассмотрении" to Color.GRAY
            LoanStates.REJECTED.toString() -> "Отказано" to Color.RED
            else -> "А была ли заявка?" to Color.YELLOW
        }

        textViewState.text = stateRu.first
        textViewState.setTextColor(stateRu.second)
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}

