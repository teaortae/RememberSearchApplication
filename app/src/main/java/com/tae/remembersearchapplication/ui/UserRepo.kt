package com.tae.remembersearchapplication.ui

import com.tae.baselibrary.repository.BaseRepository
import com.tae.remembersearchapplication.api.service.UserService

class UserRepo(val api: UserService) : BaseRepository() {

}