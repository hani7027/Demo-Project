package com.hk.atm.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.hk.atm.model.Atm
import com.hk.atm.model.Transactions


@Dao
interface AtmDao {

    @Insert
    suspend fun insert(atm: Atm)

    @Update
    suspend fun update(atm: Atm)

    @Query("select * from atm where atmId = :atmId order by atmId desc limit 1")
     fun getAtmData(atmId:Long):LiveData<Atm>


}