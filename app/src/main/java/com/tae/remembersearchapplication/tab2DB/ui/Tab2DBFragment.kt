package com.tae.remembersearchapplication.tab2DB.ui

import android.os.Bundle
import com.tae.baselibrary.ext.hideKeyboard
import com.tae.baselibrary.ui.BaseFragment
import com.tae.baselibrary.util.Log
import com.tae.remembersearchapplication.R
import com.tae.remembersearchapplication.RememberApp
import com.tae.remembersearchapplication.databinding.DbFragmentBinding
import com.tae.remembersearchapplication.ext.showDialog
import com.tae.remembersearchapplication.tab2DB.Tab2DBVMImpl
import org.koin.androidx.viewmodel.ext.android.viewModel

class Tab2DBFragment : BaseFragment<DbFragmentBinding, Tab2DBVMImpl>(R.layout.db_fragment) {

    private var userDBAdapter: UserDBAdapter? = null
    override val viewModel: Tab2DBVMImpl by viewModel()

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun initView() {
        binding.rvUser.itemAnimator = null
        binding.model = viewModel
        userDBAdapter = UserDBAdapter()
        userDBAdapter?.onCheckChangeListener = viewModel.onCheckChangeListener
    }

    override fun eventObservers() = with(viewModel) {
        //db user observer
        userTask.observe(viewLifecycleOwner) {
            if(it.isNullOrEmpty()) showToast("검색 결과가 없습니다.")
            binding.list = it
            binding.adapter = userDBAdapter
            binding.etUserName.clearFocus()
            binding.rvUser.scrollToPosition(0)
            requireContext().hideKeyboard(binding.constraintMain)
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
        requireActivity().finish()
    }
}