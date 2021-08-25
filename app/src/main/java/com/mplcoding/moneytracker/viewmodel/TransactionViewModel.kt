package com.mplcoding.moneytracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.mplcoding.moneytracker.data.Transaction
import com.mplcoding.moneytracker.data.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    application: Application,
    private val repository: TransactionRepository
): AndroidViewModel(application) {

    val getAllTransaction: LiveData<List<Transaction>> = repository.getAllTransaction().asLiveData()

    fun insertTransaction(transaction: Transaction) = viewModelScope.launch {
        repository.insertTransaction(transaction)
    }
    fun updateTransaction(transaction: Transaction) = viewModelScope.launch {
        repository.updateTransaction(transaction)
    }
    fun deleteTransaction(transaction: Transaction) = viewModelScope.launch {
        repository.deleteTransaction(transaction)
    }
}