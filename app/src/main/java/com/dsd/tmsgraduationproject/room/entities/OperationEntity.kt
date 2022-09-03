package com.dsd.tmsgraduationproject.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity (
    tableName = "operation_table",
    foreignKeys = [
        ForeignKey(
            entity = WalletEntity::class,
            parentColumns = ["id"],
            childColumns = ["id_wallet"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
 data class OperationEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    var name: String,
    var sum: Float,
    @ColumnInfo(name="type_id") var type: String,
    @ColumnInfo(name="id_wallet") val idWallet: Int
    ) {
}