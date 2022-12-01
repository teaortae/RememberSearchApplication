package com.tae.remembersearchapplication.ui

import com.tae.baselibrary.BaseActivity
import com.tae.remembersearchapplication.R
import com.tae.remembersearchapplication.RememberApp
import com.tae.remembersearchapplication.databinding.ActivityMainBinding
import com.tae.remembersearchapplication.ext.progressDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, UserVMImpl>(R.layout.activity_main) {

    override val viewModel: UserVMImpl by viewModel()

    override fun initData() {

    }

    override fun initView() {
        //로딩
        RememberApp.INSTANCE.progressDialog = progressDialog()

        binding.model = viewModel
        binding.rvUser.itemAnimator = null
    }

    override fun eventObservers() = with(viewModel) {
        userTask.observe(this@MainActivity) {
            binding.list = it.withHeader
            binding.adapter = UserAdapter()
        }
    }
}