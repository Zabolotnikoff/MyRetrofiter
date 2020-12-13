package com.example.myretrofiter.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.home.models.Loan
import com.example.myapplication.data.home.models.enums.LoanStates
import com.example.myretrofiter.R
import kotlinx.android.synthetic.main.item_loan.view.*

class LoanListAdapter: RecyclerView.Adapter<LoanListAdapter.ViewHolder>() {

    private var loanList: MutableList<Loan>? = mutableListOf()
    private var loanListener: LoanListener? = null



    fun setLoanList(list: ArrayList<Loan>) {
        loanList = list
        notifyDataSetChanged()
    }

    fun setLoanListener(listener: LoanListener) {
        loanListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_loan, parent, false)
        return ViewHolder(view, loanListener)
    }

    override fun getItemCount(): Int {
        return loanList?.size ?:0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        loanList?.get(position)?.let { holder.bind(it) }
    }

    class ViewHolder(itemView: View, private val loanListener: LoanListener?): RecyclerView.ViewHolder (itemView) {

        fun bind (item: Loan) {
            itemView.viewId.text = item.id.toString()
            itemView.viewDate.text = item.date.toString().substring(0, 10)
            itemView.viewAmount.text = item.amount.toString()

            var stateRu: Pair<String, Int> = when (item?.state.toString()) {
                LoanStates.APPROVED.toString() -> "Согласовано" to Color.GREEN
                LoanStates.REGISTERED.toString() -> "На рассмотрении" to Color.GRAY
                LoanStates.REJECTED.toString() -> "Отказано" to Color.RED
                else -> "А была ли заявка?" to Color.YELLOW
            }

            itemView.viewState.text = stateRu.first
            itemView.viewState.setTextColor(stateRu.second)

            itemView.setOnClickListener {
                loanListener?.onLoanClick(item)
            }
        }
    }

    interface LoanListener {
        fun onLoanClick(note: Loan)
    }
}