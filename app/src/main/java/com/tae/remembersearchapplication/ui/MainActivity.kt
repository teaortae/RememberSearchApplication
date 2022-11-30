package com.tae.remembersearchapplication.ui

import com.tae.baselibrary.BaseActivity
import com.tae.remembersearchapplication.R
import com.tae.remembersearchapplication.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, UserVMImpl>(R.layout.activity_main) {

    override val viewModel: UserVMImpl by viewModel()

    override fun initData() {

    }

    override fun initView() {

    }

    override fun eventObservers() {

    }
}