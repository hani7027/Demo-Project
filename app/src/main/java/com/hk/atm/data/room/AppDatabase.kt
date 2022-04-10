package com.hk.atm.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hk.atm.data.room.dao.AtmDao
import com.hk.atm.data.room.dao.TransactionDao
import com.hk.atm.data.room.dao.UserDao
import com.hk.atm.model.Atm
import com.hk.atm.model.Transactions
import com.hk.atm.model.User

@Database(entities = [User::class, Atm::class, Transactions::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getAtmDao(): AtmDao
    abstract fun getUserDao(): UserDao
    abstract fun getTransactionDao(): TransactionDao
}