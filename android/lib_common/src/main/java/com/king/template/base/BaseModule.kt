package com.king.template.base

import android.content.Context
import android.util.Log


// 初始化 TAG
const val MODULAR_INIT_TAG = "Modular_Init"

/**
 * detail: Base Module ( ContentProvider Initializer )
 * @author Shh
 * App Startup Initializer 基础 Module 类, 方便统一初始化控制、打印日志等
 */
open class BaseModule() {

    init {
        printModularInitialize(this.javaClass.simpleName)
    }

    /**
     * 打印 Modular 初始化日志
     * @param tag Module class Name
     */
    private fun printModularInitialize(tag: String) {
        Log.d(MODULAR_INIT_TAG, "$tag - initialize")
    }



}