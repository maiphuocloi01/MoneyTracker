package com.mplcoding.moneytracker.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mplcoding.moneytracker.R
import com.mplcoding.moneytracker.data.Transaction
import com.mplcoding.moneytracker.databinding.FragmentAddTransactionBinding
import com.mplcoding.moneytracker.utils.Constants
import com.mplcoding.moneytracker.utils.transformIntoDatePicker
import com.mplcoding.moneytracker.viewmodel.TransactionViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Double.parseDouble
import java.util.*

@AndroidEntryPoint
class AddTransactionFragment : Fragment() {

    private var _binding: FragmentAddTransactionBinding? =  null
    private val binding get() = _binding!!

    private val transactionViewModel: TransactionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddTransactionBinding.inflate(inflater, container, false)

        val transactionTypeAdapter = ArrayAdapter(
            requireContext(),
            R.layout.item_autocomplete_layout,
            Constants.transactionType
        )

        val tagsAdapter = ArrayAdapter(
            requireContext(),
            R.layout.item_autocomplete_layout,
            Constants.transactionTags
        )

        binding.apply {
            etTransactionType.setAdapter(transactionTypeAdapter)
            etTag.setAdapter(tagsAdapter)
            etWhen.transformIntoDatePicker(
                requireContext(),
                "dd/MM/yyyy",
                Date()
            )
        }

        binding.btnSaveTransaction.setOnClickListener {
            transactionViewModel.insertTransaction(getTransactionContent()).run {
                Toast.makeText(requireContext(), "Successfully Add Transaction", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_addTransactionFragment_to_dashboardFragment)
            }
        }

        binding.ivBack.setOnClickListener {
            findNavController().navigate(R.id.action_addTransactionFragment_to_dashboardFragment)
        }

        return binding.root
    }

    private fun getTransactionContent(): Transaction = binding.let {
        val title = it.etTitle.text.toString()
        val amount = parseDouble(it.etAmount.text.toString())
        val transactionType = it.etTransactionType.text.toString()
        val tag = it.etTag.text.toString()
        val date = it.etWhen.text.toString()
        val note = it.etNote.text.toString()
        return Transaction(title, amount, tag, date, note, transactionType)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}