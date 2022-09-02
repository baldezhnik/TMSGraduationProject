package com.dsd.tmsgraduationproject.room

import androidx.lifecycle.*
import com.dsd.tmsgraduationproject.room.entities.OperationEntity
import com.dsd.tmsgraduationproject.room.entities.WalletEntity
import kotlinx.coroutines.launch

class RoomViewModel(private val repository: Repositories) : ViewModel()  {

    val allOperations: LiveData<List<OperationEntity>> = repository.allOperations.asLiveData()
    val allWallets: LiveData<List<WalletEntity>> = repository.allWallets.asLiveData()

    fun insertOperation(operationEntity: OperationEntity) = viewModelScope.launch {
        repository.insertOperation(operationEntity)
    }

    fun insertWallet(walletEntity: WalletEntity) = viewModelScope.launch {
        repository.insertWallet(walletEntity)
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