package com.mplcoding.moneytracker.data

import javax.inject.Inject

class TransactionRepository @Inject constructor(
    private val transactionDao: TransactionDao
) {
    suspend fun insertTransaction(transaction: Transaction) {
        transactionDao.insertTransaction(transaction)
    }

    suspend fun updateTransaction(transaction: Transaction) {
        transactionDao.updateTransaction(transaction)
    }

    suspend fun deleteTransaction(transaction: Transaction) {
        transactionDao.deleteTransaction(transaction)
    }

    fun getAllTransaction() = transactionDao.getALlTransaction()

    fun getAllSingleTransaction(transactionType: String) = if (transactionType == "Overall") {
        getAllTransaction()
    } else {
        transactionDao.getAllSingleTransaction(transactionType)
    }

    fun getTransactionById(id: Int) = transactionDao.getTransactionById(id)
}