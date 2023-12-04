package com.king.template

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
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
        Constants.APP = context

        (context.applicationContext as Application?)?.registerActivityLifecycleCallbacks(object :
            Application.ActivityLifecycleCallbacks{
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            }

            override fun onActivityStarted(activity: Activity) {
            }

            override fun onActivityResumed(activity: Activity) {
                Constants.currentAc = activity
            }

            override fun onActivityPaused(activity: Activity) {
            }

            override fun onActivityStopped(activity: Activity) {
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            }

            override fun onActivityDestroyed(activity: Activity) {
                if(Constants.currentAc == activity){
                    Constants.currentAc = null
                }
            }
        });

        return CommonModule()
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}