package com.king.template.module_rn

import android.content.Context
import com.facebook.soloader.SoLoader
import com.king.template.base.BaseInitializer

class RNInitializer: BaseInitializer<RNModule>() {
    override fun create(context: Context): RNModule {
        SoLoader.init(context, false)

        return RNModule(context)
    }
}
