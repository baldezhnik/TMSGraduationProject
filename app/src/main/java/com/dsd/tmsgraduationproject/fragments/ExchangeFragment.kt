package com.dsd.tmsgraduationproject.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dsd.tmsgraduationproject.databinding.FragmentExchangeBinding
import com.dsd.tmsgraduationproject.recycleview.operation.RateListAdapter

class ExchangeFragment : Fragment() {

    private lateinit var viewModel: ExchangeViewModel

    private var _binding: FragmentExchangeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExchangeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ExchangeViewModel::class.java]

        val adapter = RateListAdapter()
        binding.rvRateList.adapter = adapter
        binding.rvRateList.layoutManager = LinearLayoutManager(context)

        viewModel.getAllRates()
        viewModel.ratesLiveData.observe(viewLifecycleOwner){rates ->
            rates.let { adapter.submitList(it) }
            binding.tvDate.text = rates[0].Date
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}