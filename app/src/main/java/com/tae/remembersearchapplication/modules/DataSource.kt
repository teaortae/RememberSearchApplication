package com.tae.remembersearchapplication.modules

import net.njobler.pref.Pref
import org.koin.dsl.module

object DataSource {
    val module = module {
        single { Pref(get()) }
    }
}