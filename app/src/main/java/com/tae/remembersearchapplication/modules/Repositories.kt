package com.tae.remembersearchapplication.modules

import com.tae.remembersearchapplication.tab1Api.Tab1APIRepo
import com.tae.remembersearchapplication.tab2DB.Tab2DBRepo
import com.tae.remembersearchapplication.main.MainRepo
import org.koin.dsl.module

object Repositories {
    val module = module {
        single { MainRepo() }
        single { Tab1APIRepo(get(), get()) }
        single { Tab2DBRepo(get()) }
    }
}