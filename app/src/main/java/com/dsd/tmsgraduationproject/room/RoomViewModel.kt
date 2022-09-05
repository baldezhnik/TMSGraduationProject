package com.dsd.tmsgraduationproject.room

import androidx.lifecycle.*
import com.dsd.tmsgraduationproject.room.entities.OperationEntity
import com.dsd.tmsgraduationproject.room.entities.WalletEntity
import kotlinx.coroutines.*

class RoomViewModel(private val repository: Repositories) : ViewModel()  {

    val allOperations: LiveData<List<OperationTuple>> = repository.allOperations.asLiveData()
    val allWallets: LiveData<List<WalletEntity>> = repository.allWallets.asLiveData()

    suspend fun getOperation(id: Long):OperationEntity {
         return repository.getOperation(id)
    }

    fun insertOperation(operationEntity: OperationEntity) = viewModelScope.launch {
        repository.insertOperation(operationEntity)
    }

    fun updateOperation(operationEntity: OperationEntity) = viewModelScope.launch {
        repository.updateOperation(operationEntity)
    }

    fun deleteOperation(operationEntity: OperationEntity) = viewModelScope.launch {
        repository.deleteOperation(operationEntity)
    }

    suspend fun getWallet(id: Int):WalletEntity {
        return repository.getWallet(id)
    }

    fun insertWallet(walletEntity: WalletEntity) = viewModelScope.launch {
        repository.insertWallet(walletEntity)
    }

    fun updateWallet(walletEntity: WalletEntity) = viewModelScope.launch {
        repository.updateWallet(walletEntity)
    }

    fun deleteWallet(walletEntity: WalletEntity) = viewModelScope.launch {
        repository.deleteWallet(walletEntity)
    }

    fun checkWallet(id: Int):Boolean {
        val bol: Boolean
        runBlocking { bol = repository.checkWallet(id) }
        return bol
    }
}

class RoomViewModelFactory(private val repository: Repositories) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RoomViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RoomViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}