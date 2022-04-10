package com.hk.atm.di

import android.content.Context
import android.content.SharedPreferences
import android.provider.SyncStateContract
import androidx.room.Room
import com.hk.atm.data.room.AppDatabase
import com.hk.atm.data.room.dao.AtmDao
import com.hk.atm.data.room.dao.TransactionDao
import com.hk.atm.data.room.dao.UserDao
import com.hk.atm.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.io.File
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun getDatabaseName(): String = Constants.DATABASE_NAME

    @Singleton
    @Provides
    fun getRoomDb(@ApplicationContext context: Context, databaseName: String): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, databaseName).createFromAsset("database/atm_db.db").build()

    @Singleton
    @Provides
    fun getUserDoa(db: AppDatabase): UserDao = db.getUserDao()

    @Singleton
    @Provides
    fun getAtmDoa(db: AppDatabase): AtmDao = db.getAtmDao()

    @Singleton
    @Provides
    fun getTransactionDoa(db: AppDatabase): TransactionDao = db.getTransactionDao()

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(
            "com.hk.atm",
            Context.MODE_PRIVATE
        )
    }

}