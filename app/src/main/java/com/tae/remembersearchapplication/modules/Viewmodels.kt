package com.tae.remembersearchapplication.modules

import net.njobler.order_complete.OrderCompVMImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModels {
    val module = module {
        viewModel { OrderCompVMImpl(get()) }
    }
}