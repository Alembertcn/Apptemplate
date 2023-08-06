package com.king.template.umeng

import android.content.Context
import com.king.template.base.BaseInitializer
import com.umeng.analytics.MobclickAgent
import com.umeng.commonsdk.UMConfigure

class UMengInitializer:BaseInitializer<UMModule>() {
    companion object
    {
        private const val UMENG_APP_KEY = "608784825844f15425ecd4fc"
        private const val UMENG_APP_SECRET = ""
    }
    override fun create(context: Context): UMModule {
        UMConfigure.init(context.applicationContext,
            UMENG_APP_KEY,"Umeng",
            UMConfigure.DEVICE_TYPE_PHONE,
            UMENG_APP_SECRET
        )
        // 选用AUTO页面采集模式
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO)
        return UMModule()
    }
}