package com.dsd.tmsgraduationproject.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dsd.tmsgraduationproject.R
import com.dsd.tmsgraduationproject.databinding.FragmentOperationBinding
import com.dsd.tmsgraduationproject.recycleview.operation.OperationListAdapter
import com.dsd.tmsgraduationproject.room.OperationsApplication
import com.dsd.tmsgraduationproject.room.RoomViewModel
import com.dsd.tmsgraduationproject.room.RoomViewModelFactory

class OperationFragment : Fragment() {
    private val roomViewModel: RoomViewModel by viewModels {
        RoomViewModelFactory((activity?.application as OperationsApplication).repository)
    }

    private var _binding: FragmentOperationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOperationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = OperationListAdapter()
        binding.rvOperationList.adapter = adapter
        binding.rvOperationList.layoutManager = LinearLayoutManager(context)

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_operationFragment_to_addOperationFragment)
        }

        roomViewModel.allOperations.observe(viewLifecycleOwner) { operations ->
            operations.let { adapter.submitList(it) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}