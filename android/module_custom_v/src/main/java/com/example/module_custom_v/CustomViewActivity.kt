package com.example.module_custom_v

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import kotlinx.android.synthetic.main.activity_main.vp2

class CustomViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vp2.adapter = object: RecyclerView.Adapter<MyViewHolder>(){
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
                return MyViewHolder(LayoutInflater.from(this@CustomViewActivity).inflate(R.layout.layout_item,parent,false))
            }

            override fun getItemCount()=3

            override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            }

        }
    }

    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    }
}