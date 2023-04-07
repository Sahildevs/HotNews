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
import com.example.teslanews.data.api.ApiService
import com.example.teslanews.data.model.BusinessNewsResponseItem
import com.example.teslanews.databinding.FragmentHomeBinding
import com.example.teslanews.ui.NewsViewModel
import com.example.teslanews.ui.adapter.BusinessNewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GeneralNewsFragment : Fragment(), BusinessNewsAdapter.Callback {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var newsAdapter: BusinessNewsAdapter? = null

    private val sharedViewMode: NewsViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showShimmerEffect()

        getNews()
        serviceObserver()
        initRecycleView()
    }

    fun initRecycleView() {
        newsAdapter = BusinessNewsAdapter(this, ArrayList())
        binding.recyclerView.adapter = newsAdapter
        newsAdapter!!.notifyDataSetChanged()
    }

    /** Service Call */
    private fun getNews() {
        lifecycleScope.launch {
            delay(2000)
            sharedViewMode.getBusinessNews()
        }
    }

    private fun serviceObserver(){
        sharedViewMode.responseBusinessNews.observe(viewLifecycleOwner) { result ->
            result.body()?.let {
                newsAdapter?.updateList(result.body()!!.news_data)

            }
        }
    }

    private fun showShimmerEffect() {
        sharedViewMode.responseBusinessNews.observe(viewLifecycleOwner) { data ->
            if (data.body() != null) {
                binding.shimmerViewContainer.stopShimmer()
                binding.shimmerViewContainer.visibility = View.GONE
            }
        }

    }


    override fun onCardClick(data: BusinessNewsResponseItem, position: Int) {
        val coverImage = data.urlToImage!!
        val sourceName = data.source?.name!!
        val content = data.description!!

        sharedViewMode.loadDetailedGeneralNews(coverImage, sourceName, content)
        findNavController().navigate(R.id.action_baseTabFragment_to_generalNewsDetailFragment)

    }

}