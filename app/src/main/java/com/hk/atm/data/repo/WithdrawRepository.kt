package com.hk.atm.data.repo

import com.hk.atm.data.room.dao.AtmDao
import com.hk.atm.data.room.dao.TransactionDao
import com.hk.atm.data.room.dao.UserDao
import com.hk.atm.model.Atm
import com.hk.atm.model.Transactions
import com.hk.atm.model.User
import javax.inject.Inject

class WithdrawRepository @Inject constructor(
    var atmDao: AtmDao,
    var transactionDao: TransactionDao,
    var userDao: UserDao
) {

    fun getUsersLastTransaction(userId: Long) = transactionDao.getUsersLastTransaction(userId)
    fun getUserTransaction(userId: Long) = transactionDao.getUserTransaction(userId)
    fun getAtmData(atmId: Long) = atmDao.getAtmData(atmId)
    suspend fun userDataInsert(user:User) = userDao.insert(user)
    suspend fun atmDataInsert(atm:Atm) = atmDao.insert(atm)
    suspend fun atmDataUpdate(atm:Atm) = atmDao.update(atm)
    suspend fun transactionDataInsert(transactions:Transactions) = transactionDao.insert(transactions)


}