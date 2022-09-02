package com.dsd.tmsgraduationproject.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dsd.tmsgraduationproject.R

class CommonFragment : Fragment() {

    companion object {
        fun newInstance() = CommonFragment()
    }

    private lateinit var viewModel: CommonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_common, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CommonViewModel::class.java)
        // TODO: Use the ViewModel
    }

}