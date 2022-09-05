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

        var type: String = binding.swOper.textOn.toString()
        binding.swOper.setOnCheckedChangeListener { compoundButton, isChecked ->
            if(isChecked){
                type = binding.swOper.textOn.toString()
                binding.swOper.text = type
            }else{
                type = binding.swOper.textOff.toString()
                binding.swOper.text = type
            }
        }

        binding.buttonSave.setOnClickListener {
            if (TextUtils.isEmpty(binding.tvEditSum.text)
                && TextUtils.isEmpty(binding.tvEditName.text)
                && TextUtils.isEmpty(binding.tvWalletId.text) ) {
                Toast.makeText(
                    context,
                    R.string.operation_empty,
                    Toast.LENGTH_LONG
                ).show()
            } else {
                if (roomViewModel.checkWallet(binding.tvWalletId.text.toString().toInt())) {
                    val operationEntity = OperationEntity(
                        0,
                        binding.tvEditName.text.toString(),
                        binding.tvEditSum.text.toString().toFloat(),
                        type,
                        binding.tvWalletId.text.toString().toInt()
                    )
                    operationEntity.let { reply ->
                        when(type) {
                            binding.swOper.textOff.toString() ->  roomViewModel.plusOperationWithWallet(
                                reply,
                                binding.tvWalletId.text.toString().toInt(),
                                binding.tvEditSum.text.toString().toFloat()
                            )
                            binding.swOper.textOn.toString() ->roomViewModel.insertOperationMinus(
                                reply,
                                binding.tvWalletId.text.toString().toInt(),
                                binding.tvEditSum.text.toString().toFloat()
                            )
                            else -> {Toast.makeText(
                                context,
                                "что то пошло не так",
                                Toast.LENGTH_LONG
                            ).show()}
                        }
                    }
                   // findNavController().popBackStack()
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}