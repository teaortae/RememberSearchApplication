package com.tae.remembersearchapplication.main

import com.tae.baselibrary.viewmodel.BaseViewModel

interface MainVM {

}

class MainVMImpl(private val repo: MainRepo) : BaseViewModel(), MainVM {


}