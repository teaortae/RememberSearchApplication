package com.tae.remembersearchapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tae.baselibrary.BaseActivity
import com.tae.remembersearchapplication.R

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}