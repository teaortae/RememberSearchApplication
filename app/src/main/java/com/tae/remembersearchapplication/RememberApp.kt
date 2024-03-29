package com.tae.remembersearchapplication

import android.app.Application
import android.app.Dialog
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.tae.baselibrary.BuildConfig
import com.tae.baselibrary.api.NetworkConst
import com.tae.baselibrary.modules.BaseRepositoryModule
import com.tae.baselibrary.modules.BaseViewModelModule
import com.tae.baselibrary.modules.NetworkModule
import com.tae.remembersearchapplication.modules.DataSource
import com.tae.remembersearchapplication.modules.NetworkService
import com.tae.remembersearchapplication.modules.Repositories
import com.tae.remembersearchapplication.modules.ViewModels
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class RememberApp : Application() {

    init {
        INSTANCE = this
    }

    companion object {
        lateinit var INSTANCE: RememberApp
    }

    var progressDialog: Dialog? = null
    var needRefresh = false

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(androidContext = this@RememberApp)
            modules(
                listOf(
                    NetworkModule.module,
                    BaseRepositoryModule.module,
                    BaseViewModelModule.module,
                    ViewModels.module,
                    Repositories.module,
                    NetworkService.module,
                    DataSource.module,
                )
            )
        }

        NetworkConst.HTTP_URL = "https://api.github.com/"

        Logger.addLogAdapter(object : AndroidLogAdapter() {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }
}