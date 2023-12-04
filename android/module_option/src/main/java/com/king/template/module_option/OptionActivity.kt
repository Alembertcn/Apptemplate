package com.king.template.module_option

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import com.alibaba.android.arouter.facade.annotation.Route
import com.king.template.constant.RouterConstants

@Route(path = RouterConstants.PAGER_OPITON_LIST)
class OptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_option)
//        val fragment = OptionListFragment()
        val fragment = OptionTabListFragment2()
        supportFragmentManager.beginTransaction().replace(R.id.flContent,fragment).commitAllowingStateLoss()
    }
    @StringRes
    fun aa(a:Int ):Unit{

    }
}