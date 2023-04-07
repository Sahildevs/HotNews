package com.example.teslanews.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.teslanews.R
import com.example.teslanews.databinding.FragmentTeslaNewsDetailBinding
import com.example.teslanews.ui.NewsViewModel


class TeslaNewsDetailFragment : Fragment() {

    private var _binding: FragmentTeslaNewsDetailBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: NewsViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTeslaNewsDetailBinding.inflate(inflater, container, false)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewmodel = sharedViewModel
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this).load(sharedViewModel.teslaCoverImg).into(binding.imageView)
    }


}