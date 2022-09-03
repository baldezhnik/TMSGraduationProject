package com.dsd.tmsgraduationproject.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dsd.tmsgraduationproject.databinding.FragmentExchangeBinding

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
        viewModel = ViewModelProvider(this).get(ExchangeViewModel::class.java)
        viewModel.getAllRates()
        viewModel.ratesLiveData.observe(viewLifecycleOwner){
            binding.tvCours.text = it.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}