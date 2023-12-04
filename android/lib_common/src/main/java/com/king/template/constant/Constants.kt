package com.king.template.constant

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.properties.Delegates

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
object Constants {


    // 由于构建lib无法获取下面两个 这里考虑编译器直接修改赋值 先只在app模块代码赋值
    var VERSION_NAME: String=""
    var VERSION_CODE: Int=-1

    /**
     * 是否支持动态切换接口地址
     */
    const val isDomain = false

    // TODO 接口地址
    const val BASE_URL = "https://github.com/"

    // TODO Bugly 申请的 AppId
    const val BUGLY_APP_ID = "5e7c47b81a"

    const val TAG = "Jenly"

    const val PAGE_SIZE = 20

    const val DOUBLE_CLICK_EXIT_TIME = 2500

    //---------------------------------------------

    const val VERIFY_CODE_COUNT_DOWN_DURATION = 60_000L
    const val VERIFY_CODE_COUNT_DOWN_INTERVAL = 1_000L

    //---------------------------------------------

    const val KEY_TITLE = "key_title"

    const val KEY_URL = "key_url"

    const val KEY_TOKEN = "key_token"

    const val KEY_USERNAME = "key_username"

    const val KEY_BEAN = "key_bean"

    const val KEY_ID = "key_id"
    const val KEY_IMAGE_URL = "key_image_url"

    const val KEY_LIST = "key_list"

    const val KEY_TYPE = "key_type"

    const val KEY_TIPS = "key_tips"

    const val KEY_MAX = "key_max"

    const val KEY_CONTENT = "key_content"

    const val KEY_CLEAR_TASK = "key_clear_task"

    const val KEY_ARRAY = "key_array"

    const val KEY_SHOW_TOOLBAR = "key_show_toolbar"

    const val KEY_SHOW_BACK = "key_show_back"

    const val KEY_TEXT = "key_text"

    //---------------------------------------------
    lateinit var APP: Context

    var currentAc: Activity?=null
}