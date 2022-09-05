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
    suspend fun insertOperation(operationEntity: OperationEntity) {
        operationDao.insertOperation(operationEntity)
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
    suspend fun insertWallet(walletEntity: WalletEntity) {
        operationDao.insertWallet(walletEntity)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
     suspend fun checkWallet(id: Int): Boolean {
        return operationDao.checkWallet(id)
    }

}