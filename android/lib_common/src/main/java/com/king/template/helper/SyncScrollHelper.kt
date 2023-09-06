package com.king.template.helper

import android.content.Context
import android.util.Log
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import android.view.ViewConfiguration
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.HorizontalScrollView
import android.widget.OverScroller
import kotlin.math.abs
import kotlin.math.max

/**
 * Created by hai
 * 同步horizontal横向滑动的辅助器
 */
class SyncScrollHelper constructor(context: Context?) : ViewTreeObserver.OnPreDrawListener,
    View.OnTouchListener {

    //滚动记录
    var scroller: OverScroller

    //速度记录
    private val mVelocityTracker: VelocityTracker

    //是否开始拖拽
    var mIsBeingDragged = false
    var scaledTouchSlop: Int
    var mMinimumVelocity: Int
    var currentScrollX:Int=0

    //系统配置
    var mMaximumVelocity: Int
    var list: MutableList<HorizontalScrollView> = ArrayList()

    init {
        scroller = OverScroller(context)
        scaledTouchSlop = ViewConfiguration.get(context).scaledTouchSlop
        mMinimumVelocity = ViewConfiguration.get(context).scaledMinimumFlingVelocity
        mMaximumVelocity = ViewConfiguration.get(context).scaledMaximumFlingVelocity
        mVelocityTracker = VelocityTracker.obtain()
    }

    fun addEle(view: HorizontalScrollView) {
        if (!list.contains(view)) {
            list.add(view)
            view.scrollTo(currentScrollX,0)
            view.setOnTouchListener(this)
//            view.viewTreeObserver?.addOnPreDrawListener(this)
        }
    }

    private fun scrollBy(dx: Int, i: Int) {
        currentScrollX += dx
        for (scrollView in list) {
            scrollView.scrollTo(currentScrollX, i)
        }
    }

    private fun scrollTo(dx: Int, dy: Int) {
        for (scrollView in list) {
            scrollView.scrollTo(dx, dy)
        }
        currentScrollX = dx;
    }

    private fun invalidate() {
        for (scrollView in list) {
            scrollView.invalidate()
        }
    }

    override fun onPreDraw(): Boolean {
        return if (scroller.computeScrollOffset() || !scroller.isFinished) {
            scrollTo(scroller.currX, scroller.currY)
            invalidate()
            true
        } else {
            removeOnPreDrawListener()
            false
        }
    }

    private fun removeOnPreDrawListener() {
        for (scrollView in list) {
            scrollView.viewTreeObserver.removeOnPreDrawListener(this)
        }
    }

    var lastX = 0f//上一次的x坐标

    override fun onTouch(view1: View, ev: MotionEvent): Boolean {
        when (ev.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                mIsBeingDragged = false
                if (!scroller.isFinished) {
                    val parent = view1.parent
                    parent?.requestDisallowInterceptTouchEvent(true)
                }

                if (!scroller.isFinished) {
                    scroller.abortAnimation()
                    scroller.forceFinished(true)
                }
                lastX = ev.x
                mVelocityTracker.clear()
                mVelocityTracker.addMovement(ev)
            }

            MotionEvent.ACTION_MOVE -> {
                val dx = lastX - ev.x
                lastX = ev.x
                if (abs(dx) >= scaledTouchSlop && !mIsBeingDragged) {
                    mIsBeingDragged = true
                }
                if (mIsBeingDragged) {
                    scrollBy(dx.toInt(), 0)
                    mVelocityTracker.addMovement(ev)
                }
            }

            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> if (mIsBeingDragged) {
                mIsBeingDragged = false
                mVelocityTracker.computeCurrentVelocity(1000, mMaximumVelocity.toFloat())
                val initialVelocity = mVelocityTracker.xVelocity.toInt()
                if (abs(initialVelocity) > mMinimumVelocity) {
                    val width = view1.width - view1.paddingLeft - view1.paddingRight
                    val right = (view1 as ViewGroup).getChildAt(0).right - view1.getPaddingLeft()
                    val maxX = max(0, right - width)
                    scroller.fling(scroller.currX, 0, -initialVelocity, 0, 0, maxX, 0, 0, 0, 0)
                    view1.invalidate()
                    view1.getViewTreeObserver().addOnPreDrawListener(this)
                }
            }
        }
        view1.parent.requestDisallowInterceptTouchEvent(mIsBeingDragged)
        return true
    }

    fun sync() {
        for (horizontalScrollView in list) {
            horizontalScrollView.scrollTo(currentScrollX,0)
        }
    }
}