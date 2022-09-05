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
import com.dsd.tmsgraduationproject.databinding.FragmentAddWalletBinding
import com.dsd.tmsgraduationproject.databinding.FragmentWalletBinding
import com.dsd.tmsgraduationproject.room.OperationsApplication
import com.dsd.tmsgraduationproject.room.RoomViewModel
import com.dsd.tmsgraduationproject.room.RoomViewModelFactory
import com.dsd.tmsgraduationproject.room.entities.OperationEntity
import com.dsd.tmsgraduationproject.room.entities.WalletEntity

class AddWalletFragment : Fragment() {

    private val roomViewModel: RoomViewModel by viewModels {
        RoomViewModelFactory((activity?.application as OperationsApplication).repository)
    }

    private var _binding: FragmentAddWalletBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddWalletBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSaveWallet.setOnClickListener {
            if (TextUtils.isEmpty(binding.tvEditNameWallet.text)) {
                Toast.makeText(
                    context,
                    R.string.operation_empty,
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val walletEntity = WalletEntity(
                    0,
                    binding.tvEditNameWallet.text.toString(),
                    binding.tvEditSumWallet.text.toString().toFloat())
                walletEntity.let { reply ->
                    roomViewModel.insertWallet(reply)
                }
                findNavController().popBackStack()

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}