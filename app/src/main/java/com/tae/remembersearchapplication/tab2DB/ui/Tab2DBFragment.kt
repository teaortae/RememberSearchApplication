package com.tae.remembersearchapplication.tab2DB.ui

import android.os.Bundle
import com.tae.baselibrary.ui.BaseFragment
import com.tae.baselibrary.util.Log
import com.tae.remembersearchapplication.R
import com.tae.remembersearchapplication.databinding.DbFragmentBinding
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
            binding.list = it.filter { f -> f.isChecked }//show user filtered by checked
            binding.adapter = userDBAdapter
        }


    }

    override fun onBackPressed() {

    }
}