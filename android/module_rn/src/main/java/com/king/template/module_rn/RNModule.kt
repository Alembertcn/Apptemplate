package com.king.template.module_rn

import android.app.Application
import android.content.Context
import androidx.startup.AppInitializer
import com.facebook.react.PackageList
import com.facebook.react.ReactInstanceManager
import com.facebook.react.ReactNativeHost
import com.facebook.react.ReactPackage
import com.king.template.CommonModule
import com.king.template.base.BaseModule

class RNModule(var context: Context) : BaseModule() {

    companion object{
        fun of(context: Context) = AppInitializer.getInstance(context).initializeComponent(RNInitializer::class.java)
    }

    var mReactNativeHost: ReactNativeHost =
        object : ReactNativeHost(context.applicationContext as Application?) {
            override fun getUseDeveloperSupport(): Boolean {
                return BuildConfig.DEBUG
            }

            override fun getPackages(): List<ReactPackage> {
                val packages: MutableList<ReactPackage> = PackageList(this).packages
                // 有一些第三方可能不能自动链接，对于这些包我们可以用下面的方式手动添加进来：
                // packages.add(new MyReactNativePackage());
                // 同时需要手动把他们添加到`settings.gradle`和 `app/build.gradle`配置文件中。
                return packages
            }

            override fun getJSMainModuleName(): String {
                return "index"
            }

            override fun getJSBundleFile(): String? {
                return super.getJSBundleFile();
//                return CodePush.getJSBundleFile()
            }
        }

    fun openDevMenu(){
        mReactNativeHost
            .reactInstanceManager
            .showDevOptionsDialog()
    }

}
