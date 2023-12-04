package com.king.template.module_option

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bin.david.form.data.column.Column
import com.bin.david.form.data.table.TableData
import com.king.template.module_option.bean.OptionEntry
import kotlinx.android.synthetic.main.option_fragment_table.table


class OptionTabListFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.option_fragment_table,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //普通列
        val column1 = Column<String>("姓名", "name")
        val column2 = Column<Int>("年龄", "age")
        val column3 = Column<Long>("更新时间", "time")
        val column4 = Column<String>("头像", "portrait")
        //如果是多层，可以通过.来实现多级查询
        val column5 = Column<String>("班级", "class.className")
        column1.drawFormat
        //组合列
        val totalColumn1: Column<*> = Column<Any?>("组合列名", column1, column2)
        totalColumn1.isFixed =true
        //表格数据 datas是需要填充的数据
        val tableData = TableData<OptionEntry>("表格名", mutableListOf(), totalColumn1, column3,column4,column5, totalColumn1,column3,column4,column5,column3,column4,column5)
        tableData.tableName = ""
        tableData.isShowCount =false
        //设置数据
//        table.setZoom(true,3);是否缩放
        table.tableData = tableData
        table.config.apply {
            isShowYSequence =false

        }
    }
}