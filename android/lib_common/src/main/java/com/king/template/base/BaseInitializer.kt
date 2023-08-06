package com.king.template.base

import androidx.startup.Initializer
import com.king.template.CommonInitializer

/**
 * detail: App Startup Initializer
 * @author Shh
 * 其他 Library、Module 继承该类进行实现
 * 内部自动要求先进行初始化 [CoreInitializer] 模块
 */
abstract class BaseInitializer<T> : Initializer<T> {

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        // 需要在 CoreInitializer 初始化后才初始化该模块
        val lists: MutableList<Class<out Initializer<*>>> =
            mutableListOf(CommonInitializer::class.java)
        dependencies_abs()?.let { lists.addAll(it) }
        return lists
    }

    open fun dependencies_abs(): MutableList<Class<out Initializer<*>>>? =null
}