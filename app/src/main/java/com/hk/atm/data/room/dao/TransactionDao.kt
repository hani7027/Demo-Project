package com.hk.atm.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.hk.atm.model.Transactions


@Dao
interface TransactionDao {

    @Insert
    suspend fun insert(transactions: Transactions)

    @Query("select * from transactions where userId = :userId ")
    fun getUserTransaction(userId: Long): LiveData<List<Transactions>>

    @Query("select * from transactions where userId = :userId order by transactionId desc limit 1")
    fun getUsersLastTransaction(userId: Long): LiveData<Transactions>
}