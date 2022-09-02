package com.dsd.tmsgraduationproject.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dsd.tmsgraduationproject.classes.Operation

@Entity (tableName = "operation_table")
 data class OperationEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    var name: String,
    var sum: Float,
    @ColumnInfo(name="type_id") var typeID: Int
    ) {

     fun toOperation(): Operation = Operation(
         id = id,
         name = name,
         sum = sum,
         typeID = typeID
     )

    companion object {
        fun createOperation(operation: Operation): OperationEntity = OperationEntity(
            id = 0,
            name = operation.name,
            sum = operation.sum,
            typeID = operation.typeID
        )
    }
}