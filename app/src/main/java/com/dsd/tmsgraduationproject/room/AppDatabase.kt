package com.dsd.tmsgraduationproject.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.dsd.tmsgraduationproject.room.entities.OperationEntity
import com.dsd.tmsgraduationproject.room.entities.WalletEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    version = 1,
    entities = [
        OperationEntity::class,
        WalletEntity::class
    ],
    exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getOperationDao(): OperationDao

    private class AppDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val operationDao = database.getOperationDao()
                    operationDao.deleteAllOperations()
                    operationDao.deleteAllWallets()
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context,
                        scope: CoroutineScope
        ): AppDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "operation_database"
                ).addCallback(AppDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

}