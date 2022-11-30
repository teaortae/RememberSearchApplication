package com.tae.remembersearchapplication.api.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun userDao(): UserDao

//        companion object {
//            @Volatile
//            private var instance: AppDatabase? = null
//            private val LOCK = Any()

//        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("ALTER TABLE printer ADD COLUMN testTable TEXT DEFAULT '' NOT NULL")
//            }
//        }
//
//        private val MIGRATION_2_3: Migration = object : Migration(2, 3) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//
//            }
//        }

//            operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
//                instance ?: buildDatabase(context).also { instance = it }
//            }

//            private fun buildDatabase(context: Context) = Room
//                .databaseBuilder(context, AppDatabase::class.java, "user.db")
//                .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
//                .build()
//        }
    }
