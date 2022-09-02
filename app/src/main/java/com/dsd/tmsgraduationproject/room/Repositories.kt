package com.dsd.tmsgraduationproject.room

import androidx.annotation.WorkerThread
import com.dsd.tmsgraduationproject.room.entities.OperationEntity
import kotlinx.coroutines.flow.Flow

class Repositories(private val operationDao: OperationDao) {

    val allOperations: Flow<List<OperationEntity>> = operationDao.getAllOperations()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(operationEntity: OperationEntity) {
        operationDao.insertOperation(operationEntity)
    }

}