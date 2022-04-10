package com.hk.atm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.hk.atm.R
import com.hk.atm.data.Response
import com.hk.atm.data.adaptor.TransactionAdaptor
import com.hk.atm.databinding.ActivityWithdrawBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WithdrawActivity : AppCompatActivity() {

    lateinit var binding: ActivityWithdrawBinding
    private val viewModel: WithdrawViewModel by viewModels()
    val adapter = TransactionAdaptor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_withdraw)
        binding.setLifecycleOwner(this)
        binding.model = viewModel
        binding.rvUserTransactions.adapter = adapter;
        viewModel.transactionHistory.observe(this, {
            adapter.submitList(it)
        })

        binding.btnWithdraw.setOnClickListener(View.OnClickListener {
            if (!TextUtils.isEmpty(binding.etWithdraw.text.toString())) {
                binding.etWithdrawHint.error = ""
                val result =
                    viewModel.withdraw(Integer.parseInt(binding.etWithdraw.text.toString()))
                when (result) {
                    is Response.Error -> {
                        binding.etWithdrawHint.error = result.message
                    }
                    is Response.Success -> {
                        binding.etWithdraw.setText("")
                        Toast.makeText(applicationContext, result.data, Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                binding.etWithdrawHint.error = "Enter Valid Amount"
            }
        })
    }
}