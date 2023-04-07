package com.example.teslanews.ui.tab_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.teslanews.R
import com.example.teslanews.data.model.AppleNewsResponseItem
import com.example.teslanews.databinding.FragmentAppleNewsBinding
import com.example.teslanews.databinding.FragmentTeslaNewsBinding
import com.example.teslanews.ui.NewsViewModel
import com.example.teslanews.ui.adapter.AppleNewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AppleNewsFragment : Fragment(), AppleNewsAdapter.Callback {

    private var _binding: FragmentAppleNewsBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel : NewsViewModel by activityViewModels()
    private var adapter: AppleNewsAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAppleNewsBinding.inflate(inflater, container, false)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showShimmerEffect()

        getAppleNews()
        serviceObserver()
        initRecyclerView()

    }

    private fun initRecyclerView() {
        adapter = AppleNewsAdapter(this, ArrayList())
        binding.recyclerView.adapter = adapter
        adapter!!.notifyDataSetChanged()
    }

    private fun getAppleNews() {
        lifecycleScope.launch {
            delay(2000)
            sharedViewModel.getAppleNews()
        }
    }

    private fun serviceObserver() {
        sharedViewModel.responseAppleNews.observe(viewLifecycleOwner) { result ->
            result.body()?.let {
                adapter?.updateList(result.body()!!.apple_news)
            }
        }
    }

    private fun showShimmerEffect() {
        sharedViewModel.responseTechNews.observe(viewLifecycleOwner) { data ->

            if (data.body() != null) {
                binding.shimmerViewContainer.stopShimmer()
                binding.shimmerViewContainer.visibility = View.GONE
            }
        }
    }

    override fun onCardClick(data: AppleNewsResponseItem, position: Int) {
        val image = data.urlToImage!!
        val source = data.source.name
        val desc = data.description!!

        sharedViewModel.loadDetailedAppleNews(image, source, desc)
        findNavController().navigate(R.id.action_baseTabFragment_to_appleNewsDetailFragment)

    }


}