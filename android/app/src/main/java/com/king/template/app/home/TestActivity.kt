package com.king.template.app.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import com.king.template.R
import com.king.template.constant.RouterConstants
import com.king.template.module_rn.RNModule
import com.king.template.module_rn.RnService


class TestActivity:AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        supportFragmentManager.beginTransaction().replace(R.id.flContent,
            ARouter.getInstance().build(RouterConstants.FRAGMENT_RN_BASE)
                .withCharSequence("page","MyReactNativeApp")
                .navigation(this) as Fragment
        ).commitAllowingStateLoss()
    }

    override fun onClick(p0: View?) {
        //        option1
        ARouter.getInstance().navigation(RnService::class.java).openDevMenu(this)
        //        option2
//        RNModule.of(this).openDevMenu()
    }


}