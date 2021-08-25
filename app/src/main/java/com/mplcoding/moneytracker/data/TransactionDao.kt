package com.mplcoding.moneytracker.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: Transaction)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTransaction(transaction: Transaction)

    @Delete
    suspend fun deleteTransaction(transaction: Transaction)

    @Query("SELECT * FROM transaction_table ORDER BY createAt DESC")
    fun getALlTransaction(): Flow<List<Transaction>>

    @Query("SELECT * FROM transaction_table WHERE transactionType == :transactionType ORDER BY createAt DESC")
    fun getAllSingleTransaction(transactionType: String): Flow<List<Transaction>>

    @Query("SELECT * FROM transaction_table WHERE id == :id")
    fun getTransactionById(id: Int): Flow<Transaction>
}