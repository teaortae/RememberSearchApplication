package com.tae.remembersearchapplication.modules

import com.tae.remembersearchapplication.ui.UserVMImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModels {
    val module = module {
        viewModel { UserVMImpl(get()) }
    }
}