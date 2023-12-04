package com.king.template.module_option.init

import android.content.Context
import com.king.template.base.BaseInitializer

class OptionInitializer: BaseInitializer<OptionModule>() {
    override fun create(context: Context): OptionModule {
       return OptionModule
    }
}