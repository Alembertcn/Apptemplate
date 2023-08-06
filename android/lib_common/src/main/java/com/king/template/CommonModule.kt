package com.king.template

import android.content.Context
import androidx.startup.AppInitializer
import com.king.template.base.BaseModule
import com.king.template.constant.Constants

open class CommonModule : BaseModule() {
    companion object {
        fun of(context: Context) = AppInitializer.getInstance(context).initializeComponent(CommonInitializer::class.java)
    }

    //
    fun initBuildConfig(buildVersionName: String, buildVersionCode: Int): Unit {
        Constants.VERSION_NAME = buildVersionName;
        Constants.VERSION_CODE = buildVersionCode;
    }
}