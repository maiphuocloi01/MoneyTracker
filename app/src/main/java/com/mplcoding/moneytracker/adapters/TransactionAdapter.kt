package com.mplcoding.moneytracker.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mplcoding.moneytracker.R
import com.mplcoding.moneytracker.data.Transaction
import com.mplcoding.moneytracker.databinding.ItemTransactionLayoutBinding
import com.mplcoding.moneytracker.utils.TransactionDiffUtil

class TransactionAdapter : RecyclerView.Adapter<TransactionAdapter.MyViewHolder>() {

    private var transactions = emptyList<Transaction>()

    class MyViewHolder(val binding: ItemTransactionLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(transaction: Transaction) {
            binding.apply {
                tvTitle.text = transaction.title
                tvDate.text = transaction.createAtDateFormat
                when (transaction.transactionType) {
                    "Income" -> {
                        tvAmount.text = "+ $".plus(transaction.amount)
                    }
                    "Expense" -> {
                        tvAmount.setTextColor(
                            ContextCompat.getColor(
                                tvAmount.context,
                                R.color.expense
                            )
                        )
                        tvAmount.text = "- $".plus(transaction.amount)
                        ivType.setImageResource(R.drawable.ic_downward)
                        ivType.setColorFilter(
                            ContextCompat.getColor(
                                ivType.context,
                                R.color.expense
                            )
                        )
//                        backgroundIcon.setBackgroundColor(
//                            ContextCompat.getColor(
//                                backgroundIcon.context,
//                                R.drawable.background_rounded_expense
//                            )
//                        )
                        backgroundIcon.setBackgroundResource(R.drawable.background_rounded_expense)
                    }
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemTransactionLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentTransaction = transactions[position]
        holder.bind(currentTransaction)
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    fun setData(newTransactions: List<Transaction>) {
        val transactionDiffUtil = TransactionDiffUtil(transactions, newTransactions)
        val diffUtil = DiffUtil.calculateDiff(transactionDiffUtil)
        transactions = newTransactions
        diffUtil.dispatchUpdatesTo(this)
    }
}