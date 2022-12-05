package com.tae.remembersearchapplication.tab1Api.ui

import android.os.Bundle
import com.tae.baselibrary.ui.BaseFragment
import com.tae.remembersearchapplication.R
import com.tae.remembersearchapplication.databinding.ApiFragmentBinding
import com.tae.remembersearchapplication.tab1Api.Tab1VMImpl
import com.tae.remembersearchapplication.tab1Api.api.data.User
import org.koin.androidx.viewmodel.ext.android.viewModel

class Tab1APIFragment : BaseFragment<ApiFragmentBinding, Tab1VMImpl>(R.layout.api_fragment) {

    private var userAdapter: UserAdapter? = null
    private var userList = listOf<User>()
    override val viewModel: Tab1VMImpl by viewModel()
    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun initView() {
        binding.model = viewModel
        binding.rvUser.itemAnimator = null
        userAdapter = UserAdapter()
        userAdapter?.onCheckChangeListener = viewModel.onCheckChangeListener
    }

    override fun eventObservers() = with(viewModel) {
        //api user search list observer
        userTask.observe(viewLifecycleOwner) {
            userList = it.withHeader
            binding.list = it.withHeader
            binding.adapter = userAdapter
        }
        userFromDbTask.observe(viewLifecycleOwner) {
            it.forEach {entity->
                userList.any {a-> a.id == entity.id }

            }
        }
    }

    override fun onBackPressed() {

    }
}