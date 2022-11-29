package com.tae.remembersearchapplication.modules

import net.njobler.alarmlist.AlarmRepo
import net.njobler.classlist.ClassRepo
import net.njobler.classlist_detail.ClassDetailRepo
import net.njobler.coupon.CouponRepo
import net.njobler.lecture.LectureHomeRepo
import net.njobler.lecture_detail.LectureDetailRepo
import net.njobler.login.LoginRepo
import net.njobler.main.MainRepo
import net.njobler.mypage.MyPageRepo
import net.njobler.order_complete.OrderCompRepo
import net.njobler.order_history.OrderHisRepo
import net.njobler.password.PasswordRepository
import net.njobler.profile.ProfileRepo
import net.njobler.register.RegisterRepo
import net.njobler.setting.SettingRepo
import net.njobler.splash.SplashRepository
import net.njobler.start_date_change.StartDateRepo
import org.koin.dsl.module

object Repositories {
    val module = module {
        single { OrderCompRepo(get()) }
    }
}