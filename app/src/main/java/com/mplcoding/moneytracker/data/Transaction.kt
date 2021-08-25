package com.mplcoding.moneytracker.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.text.DateFormat

@Entity(tableName = "transaction_table")
data class Transaction(
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "amount")
    var amount: Double,
    @ColumnInfo(name = "tag")
    var tag: String,
    @ColumnInfo(name = "date")
    var date: String,
    @ColumnInfo(name = "note")
    var note: String,
    @ColumnInfo(name = "transactionType")
    var transactionType: String,
    @ColumnInfo(name = "createAt")
    var createAt: Long = System.currentTimeMillis(),
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
) : Serializable {
    val createAtDateFormat: String
        get() = DateFormat.getDateTimeInstance().format(createAt)
}