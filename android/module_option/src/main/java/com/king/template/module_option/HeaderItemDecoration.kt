package com.king.template.module_option

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HeaderItemDecoration(private val headerView: View) : RecyclerView.ItemDecoration() {
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        // 绘制表头
        c.save()
        c.translate(0f, 0f) // 根据需要调整表头的位置
        headerView.draw(c)
        c.restore()
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        // 设置表头的高度
        outRect.top = headerView.height
    }
}

internal class HeaderScrollListener(private val headerView: View) :
    RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        // 获取当前可见的第一个条目
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager?
        val firstVisibleItemPosition = layoutManager!!.findFirstVisibleItemPosition()
        if (firstVisibleItemPosition == 0) {
            // 如果第一个条目可见，则将表头固定在屏幕顶部
            headerView.y = 0f
        } else {
            // 否则，根据需要更新表头的位置
            val firstVisibleItem = layoutManager.findViewByPosition(firstVisibleItemPosition)
            if (firstVisibleItem != null && firstVisibleItem.top < headerView.height) {
                headerView.y = -(headerView.height - firstVisibleItem.top).toFloat()
            } else {
                headerView.y = 0f
            }
        }
    }
}