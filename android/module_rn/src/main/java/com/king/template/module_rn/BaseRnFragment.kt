package com.king.template.module_rn

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.startup.AppInitializer
import com.alibaba.android.arouter.facade.annotation.Route
import com.facebook.react.ReactInstanceManager
import com.facebook.react.ReactNativeHost
import com.facebook.react.ReactRootView
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler
import com.king.template.constant.RouterConstants

/**
 * Created by Shh
 */
@Route(path = RouterConstants.FRAGMENT_RN_BASE)
class BaseRnFragment : Fragment(), DefaultHardwareBackBtnHandler {
    private var mReactRootView: ReactRootView? = null
    private var mReactInstanceManager: ReactInstanceManager? = null
    private var reactNativeHost: ReactNativeHost?=null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mReactRootView = if(mReactRootView == null) ReactRootView(context) else mReactRootView
        reactNativeHost=RNModule.of(context).mReactNativeHost
        mReactInstanceManager =reactNativeHost!!.reactInstanceManager
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val context = inflater.context
        mReactRootView = if(mReactRootView == null) ReactRootView(context) else mReactRootView
        return mReactRootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mReactRootView!!.startReactApplication(
            mReactInstanceManager,
            requireArguments().getString("page"),
            arguments
        )
    }

    override fun onResume() {
        super.onResume()
        if (reactNativeHost?.hasInstance() == true) {
            reactNativeHost?.reactInstanceManager?.onHostResume(
                activity,
                this
            )
        }
    }

    fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        var handled = false
        if (reactNativeHost?.useDeveloperSupport  == true && reactNativeHost?.hasInstance() == true) {
            if (keyCode == KeyEvent.KEYCODE_MENU) {
                reactNativeHost?.reactInstanceManager?.showDevOptionsDialog()
                handled = true
            }
        }
        return handled
    }

    fun onBackPressed() {
        if (reactNativeHost?.hasInstance() == true) {
            reactNativeHost?.reactInstanceManager?.onBackPressed()
        }
    }

    override fun onPause() {
        super.onPause()
        if (reactNativeHost?.hasInstance() == true) {
            reactNativeHost?.reactInstanceManager?.onHostPause(activity)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mReactRootView != null) {
            mReactRootView!!.unmountReactApplication()
            mReactRootView = null
        }
        if (reactNativeHost?.hasInstance() == true && (requireActivity().isFinishing || requireActivity().isDestroyed)) {
            reactNativeHost?.reactInstanceManager?.onHostDestroy(activity)
        }
    }

    override fun invokeDefaultOnBackPressed() {
        activity?.finish()
    }
}