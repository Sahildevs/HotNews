package com.example.teslanews.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.teslanews.R
import com.example.teslanews.databinding.FragmentBaseTabBinding
import com.example.teslanews.ui.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BaseTabFragment : Fragment() {

    private var _binding: FragmentBaseTabBinding? = null
    private val binding get() = _binding!!

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var adapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBaseTabBinding.inflate(inflater, container, false)

        adapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        tabLayout = binding.tabLayout
        viewPager2 = binding.viewPager2

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setupUI()
        onClick()

    }

    private fun setupUI(){

        //Adding tabs to the tab layout
        tabLayout.addTab(tabLayout.newTab().setText("General"))
        tabLayout.addTab(tabLayout.newTab().setText("Tech"))
        tabLayout.addTab(tabLayout.newTab().setText("Apple"))
        tabLayout.addTab(tabLayout.newTab().setText("Tesla"))

        //Setting the adapter to viewpager2
        viewPager2.adapter = adapter

    }

    private fun onClick() {

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    viewPager2.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })
    }


}