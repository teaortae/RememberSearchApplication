package com.tae.remembersearchapplication.tab1Api.ui

import android.os.Bundle
import androidx.lifecycle.asFlow
import com.tae.baselibrary.ui.BaseFragment
import com.tae.remembersearchapplication.R
import com.tae.remembersearchapplication.RememberApp
import com.tae.remembersearchapplication.databinding.ApiFragmentBinding
import com.tae.remembersearchapplication.ext.showDialog
import com.tae.remembersearchapplication.tab1Api.Tab1VMImpl
import com.tae.remembersearchapplication.tab1Api.api.data.User
import kotlinx.coroutines.flow.combine
import org.koin.androidx.viewmodel.ext.android.viewModel

class Tab1APIFragment : BaseFragment<ApiFragmentBinding, Tab1VMImpl>(R.layout.api_fragment) {

    private var userAdapter: UserAdapter? = null
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
        val userList = mutableListOf<User>()
        userTask.observe(viewLifecycleOwner) {
            if(it.items.isNullOrEmpty()) showToast("검색 결과가 없습니다.")
            userList.clear()
            userList.addAll(it.withHeader)
            binding.list = userList
            binding.adapter = userAdapter
            binding.etUserName.clearFocus()
            binding.rvUser.scrollToPosition(0)
        }

        alertTask.observe(viewLifecycleOwner){
            showToast("검색어를 입력하세요.")
        }
    }

    override fun onResume() {
        super.onResume()
        if (RememberApp.INSTANCE.needRefresh) {
            viewModel.getUserInfo(viewModel.userName)
            RememberApp.INSTANCE.needRefresh = false
        }
    }

    override fun onBackPressed() {

    }
}