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
import com.example.teslanews.data.model.TeslaNewsResponseItem
import com.example.teslanews.databinding.FragmentTeslaNewsBinding
import com.example.teslanews.ui.NewsViewModel
import com.example.teslanews.ui.adapter.TeslaNewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TeslaNewsFragment : Fragment(), TeslaNewsAdapter.Callback {

    private var _binding: FragmentTeslaNewsBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: NewsViewModel by activityViewModels()
    private var adapter: TeslaNewsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTeslaNewsBinding.inflate(inflater, container, false)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showShimmerEffect()

        getTeslaNews()
        serviceObserver()
        initRecycleView()
    }

    private fun initRecycleView() {
        adapter = TeslaNewsAdapter(this, ArrayList())
        binding.recyclerView.adapter = adapter
        adapter!!.notifyDataSetChanged()
    }

    /** Service call */
    private fun getTeslaNews() {
        lifecycleScope.launch {
            delay(2000)
            sharedViewModel.getTeslaNews()
        }
    }

    private fun serviceObserver() {

        sharedViewModel.responseTeslaNews.observe(viewLifecycleOwner) { result ->
            result.body()?.let {
                adapter?.updateList(result.body()!!.tesla_news)
            }
        }
    }

    private fun showShimmerEffect() {
        sharedViewModel.responseTeslaNews.observe(viewLifecycleOwner) { date ->

            if (date.body() != null) {
                binding.shimmerViewContainer.stopShimmer()
                binding.shimmerViewContainer.visibility = View.GONE
            }
        }
    }

    override fun onClick(data: TeslaNewsResponseItem, position: Int) {
        val image = data.urlToImage!!
        val source = data.source.name
        val desc = data.description!!

        sharedViewModel.loadDetailedTeslaNews(image, source, desc)
        findNavController().navigate(R.id.action_baseTabFragment_to_teslaNewsDetailFragment)
    }


}