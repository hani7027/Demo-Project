package com.hk.atm.data.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hk.atm.databinding.ItemTransactionBinding
import com.hk.atm.model.Transactions

class TransactionAdaptor : ListAdapter<Transactions, TransactionAdaptor.Holder>(TransactionDiff()) {

    class Holder(val itemTransactionBinding: ItemTransactionBinding) :
        RecyclerView.ViewHolder(itemTransactionBinding.root) {

        fun bind(item: Transactions) {
            itemTransactionBinding.data = item
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            ItemTransactionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    class TransactionDiff : DiffUtil.ItemCallback<Transactions>() {
        override fun areItemsTheSame(oldItem: Transactions, newItem: Transactions): Boolean {
            return oldItem.transactionId == newItem.transactionId
        }

        override fun areContentsTheSame(oldItem: Transactions, newItem: Transactions): Boolean {
            return oldItem == newItem
        }

    }
}