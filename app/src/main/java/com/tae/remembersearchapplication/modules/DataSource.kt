package com.tae.remembersearchapplication.modules

import androidx.room.Room
import com.tae.remembersearchapplication.api.db.AppDatabase
import org.koin.dsl.module

object DataSource {
    val module = module {
        single {
            Room.databaseBuilder(
                get(),
                AppDatabase::class.java, "github_user"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
        single {
            get<AppDatabase>().userDao()
        }
    }
}