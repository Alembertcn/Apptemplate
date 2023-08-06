package com.king.template.activity

import android.app.Activity
import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter

class SchemeFilterActivity:Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val data = intent.data
        ARouter.getInstance().build(data).navigation()
        finish()
    }
}