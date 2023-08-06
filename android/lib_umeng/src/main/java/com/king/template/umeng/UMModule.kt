package com.king.template.umeng

import android.content.Context
import androidx.startup.AppInitializer
import com.king.template.base.BaseModule

class UMModule:BaseModule(){
    companion object{
        fun of(context: Context) = AppInitializer.getInstance(context).initializeComponent(UMengInitializer::class.java)
    }

}
