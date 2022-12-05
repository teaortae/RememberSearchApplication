package com.tae.remembersearchapplication.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tae.remembersearchapplication.tab1Api.ui.Tab1APIFragment
import com.tae.remembersearchapplication.tab2DB.ui.Tab2DBFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    companion object {
        private const val NO_TABS = 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> Tab1APIFragment()
            else -> Tab2DBFragment()
        }
    }

    override fun getItemCount(): Int {
        return NO_TABS
    }
}