package com.king.template.module_option

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.king.template.helper.SyncScrollHelper
import kotlinx.android.synthetic.main.option_fragment_list.rvList
import kotlinx.android.synthetic.main.option_item.view.tv1
import kotlin.math.abs


class OptionListFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.option_fragment_list,container,false)
    }
    lateinit var helper: SyncScrollHelper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        helper = SyncScrollHelper(requireContext())
        rvList.layoutManager =object :LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false){
        }
        rvList.adapter =object :RecyclerView.Adapter<MyViewHolder>(){
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
                return MyViewHolder(View.inflate(context,R.layout.option_item,null)).apply {
                    helper.addEle(this.view as HorizontalScrollView)
                }
            }

            override fun getItemCount()=100

            override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
                helper.addEle(holder.view as HorizontalScrollView)
                holder?.itemView?.tv1.text = "$position".repeat(5)
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            rvList.setOnScrollChangeListener { _, _, scrollY, _, _ ->
                rvList.findViewHolderForAdapterPosition((rvList.layoutManager as LinearLayoutManager).findLastVisibleItemPosition())?.itemView?.scrollTo(helper.currentScrollX,0)
                if (abs(scrollY)>70 ){
                    rvList.setOnScrollChangeListener(null)
                }
            }
        }
    }


    inner class MyViewHolder(var view:View,var tv1:TextView = view.findViewById(R.id.tv1)):RecyclerView.ViewHolder(view)

}