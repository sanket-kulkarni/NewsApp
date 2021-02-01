package com.example.newsapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.newsapp.fragments.ListFragment

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> ListFragment()
            1 -> ListFragment()
            2 -> ListFragment()
            else -> ListFragment()
        }
    }

    override fun getCount(): Int {
        return 3
    }
}