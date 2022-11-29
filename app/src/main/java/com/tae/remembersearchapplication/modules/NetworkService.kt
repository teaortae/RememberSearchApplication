package com.tae.remembersearchapplication.modules

import com.tae.baselibrary.modules.NetworkModule.getService
import net.njobler.alarmlist.AlarmService
import net.njobler.classlist.api.service.ProductListService
import net.njobler.classlist_detail.api.ClassDetailService
import net.njobler.coupon.api.service.CouponService
import net.njobler.lecture.api.service.LectureHomeService
import net.njobler.lecture_detail.api.service.LectureDetailService
import net.njobler.login.api.service.LoginService
import net.njobler.main.api.service.MainService
import net.njobler.mypage.api.service.MyPageService
import net.njobler.order_complete.api.service.OrderCompService
import net.njobler.order_history.api.service.OrderService
import net.njobler.password.api.service.PasswordSearchService
import net.njobler.profile.api.service.ProfileService
import net.njobler.register.api.service.RegisterService
import net.njobler.setting.api.service.SettingService
import net.njobler.splash.SplashService
import net.njobler.start_date_change.api.service.StartDateService
import net.njobler.token.api.TokenService
import org.koin.dsl.module

object NetworkService {
    val module = module {
        single { getService<OrderCompService>(get()) }
    }
}