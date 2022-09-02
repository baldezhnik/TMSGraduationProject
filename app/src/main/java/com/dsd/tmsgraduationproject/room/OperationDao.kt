package com.dsd.tmsgraduationproject.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.dsd.tmsgraduationproject.room.entities.OperationEntity
import com.dsd.tmsgraduationproject.room.entities.WalletEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OperationDao {

    @Query("SELECT * FROM operation_table ORDER BY id DESC")
    fun getAllOperations(): Flow<MutableList<OperationEntity>>

    @Insert
    suspend fun insertOperation(operationEntity: OperationEntity)

    @Delete
    suspend fun deleteOperation(operationEntity: OperationEntity)

    @Query("DELETE FROM operation_table")
    suspend fun deleteAllOperations()

    @Query("SELECT * FROM wallet_table ORDER BY id")
    fun getAllWallets(): Flow<MutableList<WalletEntity>>

    @Insert
    suspend fun insertWallet(walletEntity: WalletEntity)
}