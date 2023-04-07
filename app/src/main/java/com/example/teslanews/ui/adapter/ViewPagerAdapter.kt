package com.example.teslanews.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.teslanews.ui.tab_fragments.AppleNewsFragment
import com.example.teslanews.ui.tab_fragments.GeneralNewsFragment
import com.example.teslanews.ui.tab_fragments.TechNewsFragment
import com.example.teslanews.ui.tab_fragments.TeslaNewsFragment

class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {

        return if (position == 0) {
            GeneralNewsFragment()
        }
        else if (position == 1) {
            TechNewsFragment()
        }
        else if (position == 2) {
            AppleNewsFragment()
        }
        else {
            TeslaNewsFragment()
        }
    }
}