package com.example.networkconnection_mvvm_hilt.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.networkconnection_mvvm_hilt.databinding.FragmentHomeBinding
import com.example.networkconnection_mvvm_hilt.viewmodel.NetworkStateViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val networkViewModel by viewModels<NetworkStateViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        networkViewModel.networkLoss.observe(viewLifecycleOwner) {
            Toast.makeText(context, "Network connection lost", Toast.LENGTH_SHORT).show()
        }
        networkViewModel.networkSuccess.observe(viewLifecycleOwner) {
            Toast.makeText(context, "Network connection establish", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}