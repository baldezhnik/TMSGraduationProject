package com.dsd.tmsgraduationproject.room

import androidx.room.*
import com.dsd.tmsgraduationproject.room.entities.OperationEntity
import com.dsd.tmsgraduationproject.room.entities.WalletEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OperationDao {

    @Query("SELECT operation_table.id as id, operation_table.type_id, operation_table.name," +
            " operation_table.sum, wallet_table.name_wallet FROM operation_table " +
            "LEFT JOIN wallet_table " +
            "ON operation_table.id_wallet = wallet_table.id" +
            " ORDER BY id DESC")
    fun getAllOperations(): Flow<MutableList<OperationTuple>>

    @Query("SELECT * FROM operation_table WHERE id = :id")
    suspend fun getOperation(id : Long): OperationEntity

    @Insert
    suspend fun insertOperation(operationEntity: OperationEntity)

    @Update
    suspend fun updateOperation(operationEntity: OperationEntity)

    @Delete
    suspend fun deleteOperation(operationEntity: OperationEntity)

    @Query("DELETE FROM operation_table")
    suspend fun deleteAllOperations()

    @Query("SELECT * FROM wallet_table ORDER BY id")
    fun getAllWallets(): Flow<MutableList<WalletEntity>>

    @Query("SELECT * FROM wallet_table WHERE id = :id")
    suspend fun getWallet(id : Int): WalletEntity

    @Insert
    suspend fun insertWallet(walletEntity: WalletEntity)

    @Update
    suspend fun updateWallet(walletEntity: WalletEntity)

    @Query("UPDATE wallet_table SET sum=sum+:sum where id=:walletid")
    suspend fun plusWallet(walletid: Int, sum:Float)

    @Query("UPDATE wallet_table SET sum=sum-:sum where id=:walletid")
    fun minusWallet(walletid: Int, sum:Float)

    @Delete
    suspend fun deleteWallet(walletEntity: WalletEntity)

    @Query("DELETE FROM wallet_table")
    suspend fun deleteAllWallets()

    @Query("SELECT EXISTS(SELECT * FROM wallet_table WHERE id = :id)")
    suspend fun checkWallet(id : Int): Boolean

    @Transaction
    suspend fun plusOperationWithWallet(operationEntity: OperationEntity, walletid: Int, sum: Float){
        insertOperation(operationEntity)
        plusWallet(walletid,sum)
    }
    @Transaction
    suspend fun insertOperationMinus(operationEntity: OperationEntity, walletid: Int, sum: Float){
        insertOperation(operationEntity)
        minusWallet(walletid,sum)
    }
}