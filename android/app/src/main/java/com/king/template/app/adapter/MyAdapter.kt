package com.king.template.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.king.template.R

class MyAdapter() : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        init {
        var tabLayout: TabLayout = itemView.findViewById(R.id.tabLayout);
//        // 获取其他单元格的视图并设置内容
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false);
        return  MyViewHolder(view);
    }

    override fun getItemCount()=20

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // 设置 TabLayout 和其他单元格的内容
    }
}

//public class MyViewHolder extends RecyclerView.ViewHolder {
//    public TabLayout tabLayout;
//
//    public MyViewHolder(View itemView) {
//        super(itemView);
//        tabLayout = itemView.findViewById(R.id.tabLayout);
//        // 获取其他单元格的视图并设置内容
//        ...
//    }
//}
//}