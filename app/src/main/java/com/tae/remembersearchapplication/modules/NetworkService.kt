package com.tae.remembersearchapplication.modules

import com.tae.baselibrary.modules.NetworkModule.getService
import com.tae.remembersearchapplication.api.service.UserService
import org.koin.dsl.module

object NetworkService {
    val module = module {
        single { getService<UserService>(get()) }
    }
}