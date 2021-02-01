package com.example.newsapp

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.databinding.NavDrawerHeaderMainBinding
import com.example.newsapp.model.WeatherBaseData
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var binding:ActivityMainBinding

    val viewModel: MainActivityViewModel by lazy {
        ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setTitleTextColor(getColor(R.color.blue));
        //create default navigation drawer toggle
        val toggle = ActionBarDrawerToggle(
            this, binding.drawerLayout, binding.toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        //setting Tab layout (number of Tabs = number of ViewPager pages)
        for (i in 0..2) {
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText(viewModel.pageTitle[i]))
        }

        //set gravity for tab bar

        //set gravity for tab bar
        binding.tabLayout.setTabGravity(TabLayout.GRAVITY_FILL)
        binding.navView.setNavigationItemSelectedListener(this)

        //set viewpager adapter

        //set viewpager adapter
        val pagerAdapter = ViewPagerAdapter(supportFragmentManager)
        binding.viewPager.setAdapter(pagerAdapter)

        //change Tab selection when swipe ViewPager

        //change Tab selection when swipe ViewPager
        binding.viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout))

        //change ViewPager page when tab selected

        //change ViewPager page when tab selected
        binding.tabLayout.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
           override fun onTabSelected(tab: TabLayout.Tab) {
                binding.viewPager.setCurrentItem(tab.position)
            }

            override  fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
        val _bind: NavDrawerHeaderMainBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.nav_drawer_header_main,
            binding.navView,
            false
        )
        binding.navView.addHeaderView(_bind.getRoot())
        viewModel.data.observe(this, Observer {
            Log.e("MainActivity", it.toString())
            _bind.model= it
        })
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.home) {
            binding.viewPager.setCurrentItem(0)
        } else if (id == R.id.clr_bookmark) {
            viewModel.clearBookMarks()
            Toast.makeText(this@MainActivity,"Bookmarks cleared!!", Toast.LENGTH_LONG).show()
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}