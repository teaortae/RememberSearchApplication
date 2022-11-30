package com.tae.remembersearchapplication.ui

import com.tae.baselibrary.BaseActivity
import com.tae.baselibrary.util.Log
import com.tae.remembersearchapplication.R
import com.tae.remembersearchapplication.databinding.ActivityMainBinding
import com.tae.remembersearchapplication.user
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, UserVMImpl>(R.layout.activity_main) {

    override val viewModel: UserVMImpl by viewModel()

    override fun initData() {

    }

    override fun initView() {
        binding.model = viewModel
    }

    override fun eventObservers() = with(viewModel) {

        userTask.observe(this@MainActivity) {
            binding.rvUser.withModels {
                it.items.map { user ->

                    user {
                        id(user.id)
                        item(user)
                    }
                }
            }
        }
    }
}