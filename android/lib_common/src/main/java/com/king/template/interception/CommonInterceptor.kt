package com.king.template.interception

import android.content.Context
import android.util.Log
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor

@Interceptor(priority = 1, name = "CommonInterceptor")
class CommonInterceptor:IInterceptor {
    companion object{
        const val TAG = "IInterceptor"
    }
    override fun init(context: Context?) {
        Log.d(TAG,"CommonInterceptor init")
    }

    override fun process(postcard: Postcard?, callback: InterceptorCallback?) {
        Log.d(TAG,"process ${postcard?.path}")
        // The above two types need to call at least one of them, otherwise it will not continue routing

        callback?.onContinue(postcard);
        // Interrupt routing process
        // callback.onInterrupt(new RuntimeException("Something exception"));


    }

}