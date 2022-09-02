package com.dsd.tmsgraduationproject.room

import androidx.lifecycle.*
import com.dsd.tmsgraduationproject.room.entities.OperationEntity
import kotlinx.coroutines.launch

class RoomViewModel(private val repository: Repositories) : ViewModel()  {

    val allOperations: LiveData<List<OperationEntity>> = repository.allOperations.asLiveData()

    fun insert(operationEntity: OperationEntity) = viewModelScope.launch {
        repository.insert(operationEntity)
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