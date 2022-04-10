package com.hk.atm.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import com.hk.atm.data.Response
import com.hk.atm.data.repo.WithdrawRepository
import com.hk.atm.model.Atm
import com.hk.atm.model.Transactions
import com.hk.atm.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WithdrawViewModel @Inject constructor(
    val repository: WithdrawRepository
) : ViewModel() {

    val userId: Long = 1;
    val atmId: Long = 1;
    val lastTransaction = repository.getUsersLastTransaction(userId)
    val transactionHistory = repository.getUserTransaction(userId)
    val atmData = repository.getAtmData(atmId);
//
//    fun initdata() {
//        viewModelScope.launch {
//            if (repository.atmDao.getAtmData(atmId).value == null) {
//                Log.e("inits", "okok");
//                repository.userDataInsert(User(0, "Hani", "Kachhadiya"))
//                repository.atmDataInsert(Atm(0, 50000, 75, 50, 25, 10))
//            }
//        }
//    }


    fun withdraw(withdrawAmount: Int): Response<String> {


        if (withdrawAmount % 100 != 0)
            return Response.Error("Invalid Amount")

        if (atmData.value!!.balance < withdrawAmount) {
            return Response.Error("Insufficient Balance")
        }
        var amount = withdrawAmount
        var notCounter2000: Int = 0;
        var notCounter500: Int = 0;
        var notCounter200: Int = 0;
        var notCounter100: Int = 0;
        if (amount >= 2000) {
            notCounter2000 = amount / 2000
            if (notCounter2000 > atmData.value!!.cnt2000) {
                notCounter2000 = atmData.value!!.cnt2000;
            }
            amount = amount - notCounter2000 * 2000
        }
        if (amount >= 500) {
            notCounter500 = amount / 500
            if (notCounter500 > atmData.value!!.cnt500) {
                notCounter500 = atmData.value!!.cnt500;
            }
            amount = amount - notCounter500 * 500
        }
        if (amount >= 200) {
            notCounter200 = amount / 200
            if (notCounter200 > atmData.value!!.cnt200) {
                notCounter200 = atmData.value!!.cnt200;
            }
            amount = amount - notCounter200 * 200
        }
        if (amount >= 100) {
            notCounter100 = amount / 100
            if (notCounter100 > atmData.value!!.cnt100) {
                notCounter100 = atmData.value!!.cnt100;
            }
            amount = amount - notCounter100 * 100
        }
        if (amount == 0) {
            viewModelScope.launch {
                repository.atmDataUpdate(
                    Atm(
                        atmId,
                        (atmData.value!!.balance - withdrawAmount),
                        (atmData.value!!.cnt100 - notCounter100),
                        (atmData.value!!.cnt200 - notCounter200),
                        (atmData.value!!.cnt500 - notCounter500),
                        (atmData.value!!.cnt2000 - notCounter2000)
                    )
                )
            }

            viewModelScope.launch {
                repository.transactionDataInsert(
                    Transactions(
                        0,
                        atmId,
                        userId,
                        withdrawAmount,
                        notCounter100,
                        notCounter200,
                        notCounter500,
                        notCounter2000
                    )
                )
            }

            return Response.Success("Transaction Done SuccessFully!!")
        } else {
            return Response.Error("Invalid Transaction!!")

        }
    }
}