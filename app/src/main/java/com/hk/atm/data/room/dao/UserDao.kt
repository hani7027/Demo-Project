package com.hk.atm.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import com.hk.atm.model.User


@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)
}