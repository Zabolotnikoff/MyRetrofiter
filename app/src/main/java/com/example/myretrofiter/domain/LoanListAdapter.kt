package com.example.myretrofiter.domain

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.home.models.Loan
import com.example.myapplication.data.home.models.enums.LoanStates
import com.example.myretrofiter.R
import kotlinx.android.synthetic.main.item_loan_list.view.*


class LoanListAdapter(private val clickListener: (Loan) -> Unit) :  RecyclerView.Adapter<LoanListAdapter.ViewHolder>() {

    private var loanList: MutableList<Loan>? = mutableListOf()

    fun setLoanList(list: ArrayList<Loan>?) {
        loanList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_loan_list, parent, false)
        return ViewHolder(view, clickListener)
    }

    override fun getItemCount(): Int {
        return loanList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        loanList?.get(position)?.let { holder.bind(it) }
    }

    class ViewHolder(itemView: View, private val loanListener: (Loan) -> Unit) : RecyclerView.ViewHolder(itemView) {

        fun bind(model: Loan) {
            itemView.viewId.text = model.id.toString()
            itemView.viewDate.text = model.date.toString().substring(0, 10)
            itemView.viewAmount.text = model.amount.toString()

            val stateRu: Pair<String, Int> = when (model.state.toString()) {
                LoanStates.APPROVED.toString() -> "Согласовано" to Color.GREEN
                LoanStates.REGISTERED.toString() -> "На рассмотрении" to Color.GRAY
                LoanStates.REJECTED.toString() -> "Отказано" to Color.RED
                else -> "А была ли заявка?" to Color.YELLOW
            }

            itemView.viewState.text = stateRu.first
            itemView.viewState.setTextColor(stateRu.second)

            itemView.setOnClickListener {
                loanListener(model)
            }
        }
    }
}