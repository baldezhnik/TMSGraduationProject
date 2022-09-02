package com.dsd.tmsgraduationproject.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dsd.tmsgraduationproject.R
import com.dsd.tmsgraduationproject.databinding.FragmentAddOperationBinding
import com.dsd.tmsgraduationproject.room.OperationsApplication
import com.dsd.tmsgraduationproject.room.RoomViewModel
import com.dsd.tmsgraduationproject.room.RoomViewModelFactory
import com.dsd.tmsgraduationproject.room.entities.OperationEntity

class AddOperationFragment : Fragment() {
    private val roomViewModel: RoomViewModel by viewModels {
        RoomViewModelFactory((activity?.application as OperationsApplication).repository)
    }

    private var _binding: FragmentAddOperationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddOperationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSave.setOnClickListener {
            if (TextUtils.isEmpty(binding.tvEditName.text)) {
                Toast.makeText(
                    context,
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val operationEntity = OperationEntity(
                    0,
                    binding.tvEditName.text.toString(),
                    binding.tvEditSum.text.toString().toFloat(),
                    binding.tvEditTypeId.text.toString().toInt())
                operationEntity.let { reply ->
                    roomViewModel.insertOperation(reply)
                }
                findNavController().navigate(R.id.action_addOperationFragment_to_operationFragment)

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}