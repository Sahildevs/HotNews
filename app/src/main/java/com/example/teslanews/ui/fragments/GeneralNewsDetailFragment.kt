package com.example.teslanews.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.teslanews.R
import com.example.teslanews.databinding.FragmentGeneralNewsDetailBinding
import com.example.teslanews.ui.NewsViewModel


class GeneralNewsDetailFragment : Fragment() {
    private var _binding: FragmentGeneralNewsDetailBinding? = null
    private val binding get() = _binding!!

    private val sharedViewMode: NewsViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGeneralNewsDetailBinding.inflate(inflater, container, false)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewmodel = sharedViewMode
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this).load(sharedViewMode.businessCoverImg).into(binding.imageView)
    }




}