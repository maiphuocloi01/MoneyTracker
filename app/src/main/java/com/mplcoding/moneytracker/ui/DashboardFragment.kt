package com.mplcoding.moneytracker.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mplcoding.moneytracker.R
import com.mplcoding.moneytracker.adapters.TransactionAdapter
import com.mplcoding.moneytracker.databinding.FragmentDashboardBinding
import com.mplcoding.moneytracker.viewmodel.TransactionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private val mAdapter by lazy { TransactionAdapter() }
    private val viewModel: TransactionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_addTransactionFragment)
        }

        setupRecyclerView()
        viewModel.getAllTransaction.observe(viewLifecycleOwner, {database->
            mAdapter.setData(database)
        })
        return binding.root
    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter = mAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}