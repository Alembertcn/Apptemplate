package com.king.template.module_rn

import android.content.Context
import androidx.startup.AppInitializer
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.template.IProvider
import com.king.template.constant.RouterConstants

@Route(path = RouterConstants.SERVER_RN)
class RnService:IProvider {
    override fun init(context: Context?) {
    }

    fun openDevMenu(context: Context){
        RNModule.of(context).openDevMenu()
    }
}