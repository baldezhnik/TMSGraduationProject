package com.dsd.tmsgraduationproject.room

import androidx.annotation.WorkerThread
import com.dsd.tmsgraduationproject.room.entities.OperationEntity
import com.dsd.tmsgraduationproject.room.entities.WalletEntity
import kotlinx.coroutines.flow.Flow

class Repositories(private val operationDao: OperationDao) {

    val allOperations: Flow<List<OperationTuple>> = operationDao.getAllOperations()
    val allWallets: Flow<List<WalletEntity>> = operationDao.getAllWallets()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getOperation(int: Long): OperationEntity {
        return operationDao.getOperation(int)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateOperation(operationEntity: OperationEntity) {
        operationDao.updateOperation(operationEntity)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteOperation(operationEntity: OperationEntity) {
        operationDao.deleteOperation(operationEntity)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getWallet(int: Int): WalletEntity {
        return operationDao.getWallet(int)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertWallet(walletEntity: WalletEntity) {
        operationDao.insertWallet(walletEntity)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateWallet(walletEntity: WalletEntity) {
        operationDao.updateWallet(walletEntity)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteWallet(walletEntity: WalletEntity) {
        operationDao.deleteWallet(walletEntity)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
     suspend fun checkWallet(id: Int): Boolean {
        return operationDao.checkWallet(id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun plusOperationWithWallet(operationEntity: OperationEntity, walletid: Int, sum: Float) {
        operationDao.plusOperationWithWallet(operationEntity,walletid, sum)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertOperationMinus(operationEntity: OperationEntity, walletid: Int, sum: Float) {
        operationDao.insertOperationMinus(operationEntity,walletid, sum)
    }

}