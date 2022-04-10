package com.hk.atm.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.inject.Inject

@Entity
data class User @Inject constructor(
    @PrimaryKey(autoGenerate = true)
    var userId: Long,
    var firstName: String,
    var lastName: String,
)