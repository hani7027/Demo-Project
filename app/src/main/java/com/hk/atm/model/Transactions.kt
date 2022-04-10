package com.hk.atm.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Transactions(
    @PrimaryKey(autoGenerate = true)
    var transactionId: Long,
    var atmId: Long,
    var userId: Long,
    var balance: Int,
    var cnt100: Int,
    var cnt200: Int,
    var cnt500: Int,
    var cnt2000: Int
)
