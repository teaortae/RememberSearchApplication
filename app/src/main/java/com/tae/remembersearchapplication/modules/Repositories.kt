package com.tae.remembersearchapplication.modules

import com.tae.remembersearchapplication.ui.UserRepo
import org.koin.dsl.module

object Repositories {
    val module = module {
        single { UserRepo(get()) }
    }
}