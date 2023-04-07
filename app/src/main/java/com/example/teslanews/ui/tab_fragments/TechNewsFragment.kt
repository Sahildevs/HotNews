package com.example.teslanews.ui.tab_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.teslanews.R
import com.example.teslanews.data.model.TechNewsResponseItem
import com.example.teslanews.databinding.FragmentTechNewsBinding
import com.example.teslanews.ui.NewsViewModel
import com.example.teslanews.ui.adapter.TechNewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TechNewsFragment : Fragment(), TechNewsAdapter.Callback {

    private var _binding: FragmentTechNewsBinding? = null
    private val binding get() = _binding!!

    private var adapter: TechNewsAdapter? = null

    private val sharedViewModel: NewsViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTechNewsBinding.inflate(inflater, container, false)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showShimmerEffect()

        getTechNew()
        serviceObserver()
        initRecyclerView()

    }

    private fun initRecyclerView() {

        adapter = TechNewsAdapter(this, ArrayList())
        binding.recyclerView.adapter = adapter
        adapter!!.notifyDataSetChanged()
    }

    /** Service Call */
    private fun getTechNew() {
        lifecycleScope.launch {
            delay(2000)
            sharedViewModel.getTechNews()
        }
    }

    private fun serviceObserver() {

        sharedViewModel.responseTechNews.observe(viewLifecycleOwner) { result ->
            result.body()?.let {
                adapter?.updateList(result.body()!!.tech_news)
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



    override fun onCardClick(data: TechNewsResponseItem, position: Int) {
        val image  = data.urlToImage!!
        val source = data.source.name
        val desc = data.description!!

        sharedViewModel.loadDetailedTechNews(image, source, desc)
        findNavController().navigate(R.id.action_baseTabFragment_to_techNewsDetailFragment)
    }


}