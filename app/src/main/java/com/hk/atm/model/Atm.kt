package com.hk.atm.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.inject.Inject

@Entity
data class Atm @Inject constructor(
    @PrimaryKey(autoGenerate = true)
    var atmId: Long,
    var balance: Int,
    var cnt100: Int,
    var cnt200: Int,
    var cnt500: Int,
    var cnt2000: Int
)
