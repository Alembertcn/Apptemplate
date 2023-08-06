package com.king.template

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.startup.Initializer
import com.alibaba.android.arouter.launcher.ARouter
import com.king.template.component.BuildConfig
import com.king.template.constant.Constants
import com.tencent.bugly.Bugly

class CommonInitializer: Initializer<CommonModule> {
    override fun create(context: Context): CommonModule {
        ARouter.init(context.applicationContext as Application?)
        Bugly.init(context, Constants.BUGLY_APP_ID, BuildConfig.DEBUG)

        return CommonModule()
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}