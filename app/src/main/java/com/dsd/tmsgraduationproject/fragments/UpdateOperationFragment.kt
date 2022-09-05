package com.dsd.tmsgraduationproject.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dsd.tmsgraduationproject.R
import com.dsd.tmsgraduationproject.databinding.FragmentUpdateOperationBinding
import com.dsd.tmsgraduationproject.room.OperationsApplication
import com.dsd.tmsgraduationproject.room.RoomViewModel
import com.dsd.tmsgraduationproject.room.RoomViewModelFactory
import com.dsd.tmsgraduationproject.room.entities.OperationEntity
import kotlinx.coroutines.*


class UpdateOperationFragment : Fragment() {

    companion object {
        const val OPERATION_ID = "operationId"
    }

    private  lateinit var operationEntity:OperationEntity
    private val roomViewModel: RoomViewModel by viewModels {
        RoomViewModelFactory((activity?.application as OperationsApplication).repository)
    }
    private var operationId: Long = 1
    private lateinit var type: String

    private var _binding: FragmentUpdateOperationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        operationId = arguments?.getLong(OPERATION_ID)!!
        _binding = FragmentUpdateOperationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getOperationEntity()
        type = binding.swOper.textOn.toString()
        binding.swOper.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                type = binding.swOper.textOn.toString()
                binding.swOper.text = type
            }else{
                type = binding.swOper.textOff.toString()
                binding.swOper.text = type
            }
        }

        binding.buttonUpdate.setOnClickListener {
           updateOperation()
        }
        binding.buttonDelete.setOnClickListener {
            roomViewModel.deleteOperation(operationEntity)
            findNavController().popBackStack()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getOperationEntity(){
        runBlocking {
            operationEntity = roomViewModel.getOperation(operationId)
            binding.tvEditName.setText(operationEntity.name,TextView.BufferType.EDITABLE)
            binding.tvEditSum.setText(operationEntity.sum.toString(),TextView.BufferType.EDITABLE)
            binding.tvWalletId.setText(operationEntity.idWallet.toString(),TextView.BufferType.EDITABLE)
            binding.buttonUpdate.isEnabled = true
            binding.buttonDelete.isEnabled = true
            binding.tvDownload.visibility = View.GONE
        }
    }

    private fun updateOperation() {
        if (TextUtils.isEmpty(binding.tvEditSum.text)
            and TextUtils.isEmpty(binding.tvEditName.text)
            and TextUtils.isEmpty(binding.tvWalletId.text) ) {
            Toast.makeText(
                context,
                R.string.operation_empty,
                Toast.LENGTH_LONG
            ).show()
        } else {
            if (roomViewModel.checkWallet(binding.tvWalletId.text.toString().toInt())) {
                val operationEntity = OperationEntity(
                    operationId,
                    binding.tvEditName.text.toString(),
                    binding.tvEditSum.text.toString().toFloat(),
                    type,
                    binding.tvWalletId.text.toString().toInt()
                )
                operationEntity.let { reply ->
                    roomViewModel.updateOperation(reply)
                }
                findNavController().popBackStack()
            }else{
                Toast.makeText(
                    context,
                    R.string.wallet_empty,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

}