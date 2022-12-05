package com.tae.remembersearchapplication.modules

import com.tae.remembersearchapplication.tab1Api.Tab1VMImpl
import com.tae.remembersearchapplication.tab2DB.Tab2DBVMImpl
import com.tae.remembersearchapplication.main.MainVMImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModels {
    val module = module {
        viewModel { MainVMImpl(get()) }
        viewModel { Tab1VMImpl(get()) }
        viewModel { Tab2DBVMImpl(get()) }
    }
}