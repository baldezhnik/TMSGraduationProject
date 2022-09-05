package com.dsd.tmsgraduationproject.room

import androidx.room.ColumnInfo

data class OperationTuple(
    @ColumnInfo(name="id")val id: Long,
    @ColumnInfo(name="type_id") var type: String,
    var name: String,
    var sum: Float,
    @ColumnInfo(name = "name_wallet")var nameWallet: String,

)