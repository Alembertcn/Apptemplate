package com.king.template.app.home

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.king.template.R


class GirdListDemoFragment:Fragment()  {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.grid_list_demo_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       var recyclerView=view.findViewById<RecyclerView>(R.id.recyclerView);
           // 创建网格布局，并设置给 RecyclerView
           val layoutManager = GridLayoutManager(context, 5)
           layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
               override fun getSpanSize(position: Int): Int {
                   return if (position % 5 == 0) { // 第一列单元格跨度为 2
                       2
                   } else { // 其他单元格跨度为 1
                       1
                   }
               }
           }
           recyclerView.layoutManager = layoutManager

           // 添加分割线
           val itemDecoration = MyItemDecoration()
           recyclerView.addItemDecoration(itemDecoration)
        // 创建适配器并设置给 RecyclerView
        val adapter = MyAdapter()
        recyclerView.adapter = adapter
    }
    class MyAdapter : RecyclerView.Adapter<MyViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_cell, parent, false)
            return MyViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.bindData(position)
        }

        override fun getItemCount(): Int {
            return 50 // 总共有 50 个单元格
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.textView)

        fun bindData(position: Int) {
            textView.text = "Cell $position"
        }
    }

    class MyItemDecoration : RecyclerView.ItemDecoration() {
        private val paint = Paint()

        init {
            paint.color = Color.BLACK
            paint.style = Paint.Style.STROKE
            paint.strokeWidth = 2f
        }

        override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            super.onDraw(c, parent, state)

            // 绘制表格边框
            c.drawRect(parent.left.toFloat(), parent.top.toFloat(), parent.right.toFloat(), parent.bottom.toFloat(), paint)

            // 绘制横向分割线
            for (i in 1 until parent.childCount / 5) {
                val child = parent.getChildAt(i * 5 - 1)
                c.drawLine(child.right.toFloat(), child.top.toFloat(), child.right.toFloat(), child.bottom.toFloat(), paint)
            }

            // 绘制纵向分割线
            for (i in 1 until 5) {
                val child = parent.getChildAt(i)
                c.drawLine(child.left.toFloat(), child.top.toFloat(), child.left.toFloat(), child.bottom.toFloat(), paint)
            }
        }
    }

    companion object {
        fun newInstance(s: String, b: Boolean)=GirdListDemoFragment()
    }
}