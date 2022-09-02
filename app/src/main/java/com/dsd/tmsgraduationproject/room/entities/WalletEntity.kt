package com.dsd.tmsgraduationproject.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wallet_table")
data class WalletEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var name: String,
    var sum: Float
) {


}