package com.tae.remembersearchapplication.main

import com.google.android.material.tabs.TabLayoutMediator
import com.tae.baselibrary.BaseActivity
import com.tae.remembersearchapplication.R
import com.tae.remembersearchapplication.RememberApp
import com.tae.remembersearchapplication.databinding.ActivityMainBinding
import com.tae.remembersearchapplication.ext.progressDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainVMImpl>(R.layout.activity_main) {

    private val tabTitleArray = arrayOf(
        "API",
        "로컬",
    )

    override val viewModel: MainVMImpl by viewModel()

    override fun initData() {

    }

    override fun initView() {
        //로딩 init
        RememberApp.INSTANCE.progressDialog = progressDialog()

        val viewPager = binding.pager
        val tabLayout = binding.tabLayout

        //viewpager adapter
        viewPager.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTitleArray[position]
        }.attach()
    }

    override fun eventObservers() {

    }
}