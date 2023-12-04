package com.king.template.module_option

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.facade.annotation.Route
import com.bin.david.form.core.SmartTable
import com.bin.david.form.core.TableConfig
import com.bin.david.form.data.CellInfo
import com.bin.david.form.data.column.Column
import com.bin.david.form.data.format.bg.BaseCellBackgroundFormat
import com.bin.david.form.data.format.grid.BaseGridFormat
import com.bin.david.form.data.format.title.TitleDrawFormat
import com.bin.david.form.data.style.LineStyle
import com.bin.david.form.utils.DrawUtils
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.king.template.constant.RouterConstants
import com.king.template.module_option.bean.ExpirationItem
import com.king.template.module_option.bean.OptionExpirationEntry
import com.king.template.module_option.bean.OptionTableEntry
import com.king.template.net.HttpResponse
import com.king.template.utils.CheckUtils
import kotlinx.android.synthetic.main.option_fragment_table.btnOrder
import kotlinx.android.synthetic.main.option_fragment_table.btnOrder2
import kotlinx.android.synthetic.main.option_fragment_table.btnOrder3
import kotlinx.android.synthetic.main.option_fragment_table.btnOrder4
import kotlinx.android.synthetic.main.option_fragment_table.btnOrder5
import kotlinx.android.synthetic.main.option_fragment_table.table
import kotlinx.android.synthetic.main.option_fragment_table.tbLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import java.math.BigDecimal
import kotlin.math.abs

@Route(path = RouterConstants.FRAGMENT_OPTION_LIST)
class OptionTabListFragment2 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.option_fragment_table, container, false)
    }

    private lateinit var expirationList: List<ExpirationItem>
    var currentSelectDate=""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //
        var textSpanned21 = SpannableString("Hello");
        textSpanned21.setSpan(
            ForegroundColorSpan(Color.BLUE),
            0,
            textSpanned21.length,
            Spanned.SPAN_EXCLUSIVE_INCLUSIVE
        );
        requestMovieDetails()
        table.setOnColumnClickListener{
            print(it.top);
        }
        table.config.apply {
            isShowYSequence = false
            isShowXSequence = false
            isShowTableTitle = false
            verticalPadding = 30
            columnTitleVerticalPadding = 30
            textLeftOffset = 10
            contentCellBackgroundFormat = object : BaseCellBackgroundFormat<CellInfo<Any>>() {
                override fun getBackGroundColor(t: CellInfo<Any>?): Int {
                    return Color.TRANSPARENT
                }

                override fun getTextColor(t: CellInfo<Any>?): Int {
                    if (t?.data==null) return super.getTextColor(t);

                    if (t?.col == 4) {
                        return if ((t?.data as Double?) ?: 0.0 > 10) Color.RED else Color.GREEN
                    }
                    return super.getTextColor(t)
                }
            }
            tableGridFormat = object : BaseGridFormat() {
                override fun isShowVerticalLine(
                    col: Int,
                    row: Int,
                    cellInfo: CellInfo<*>?
                ): Boolean {
                    return col == 0
                }

                override fun isShowColumnTitleVerticalLine(col: Int, column: Column<*>?): Boolean {
                    return col == 0
                }

                override fun drawTableBorderGrid(
                    canvas: Canvas?,
                    left: Int,
                    top: Int,
                    right: Int,
                    bottom: Int,
                    paint: Paint?
                ) {
//                    super.drawTableBorderGrid(canvas, left, top, right, bottom, paint)
                }


                override fun drawGridPath(
                    canvas: Canvas?,
                    rect: Rect?,
                    paint: Paint?,
                    isShowHorizontal: Boolean,
                    isShowVertical: Boolean
                ) {
                    super.drawGridPath(canvas, rect, paint, isShowHorizontal, isShowVertical)
                    var bottom = table.provider.getPointLocation(5.0,1.0)[1]
                    val isBlock = abs(bottom - rect!!.bottom) <10f
                    if(isShowHorizontal){
                        if(isBlock){
                            paint?.style = Paint.Style.FILL
                        }
                        canvas?.drawRect(Rect(rect!!.left,rect.bottom-(if(isBlock) 10 else 0),rect.right,rect.bottom+(if(isBlock) 10 else 0)),paint!!)
                    }
                }
            }
        }
        Log.d("testHeight", table.matrixHelper.zoomRect.bottom.toString())
        btnOrder.setOnClickListener {
            Log.d("testHeight", table.matrixHelper.zoomRect.bottom.toString())
//            (table.tableData.getColumnByID(3) as Column<Double>).setComparator{o1,o2->(o1-o2).toInt()}
            table.setSortColumn(table.tableData.getColumnByID(3), false)

            Log.d("testHeight", table.matrixHelper.zoomRect.bottom.toString())
            table.layoutParams = table.layoutParams.apply {
                height = table.matrixHelper.zoomRect.bottom
            }
            table.requestLayout()
        }
        btnOrder2.setOnClickListener {
            table.setSortColumn(table.tableData.getColumnByID(3), true)
        }
        btnOrder3.setOnClickListener {
            requestMovieDetails()
        }
        btnOrder4.setOnClickListener {
            (table.tableData.t as List<OptionTableEntry>?)?.forEach {
                it.currentPrice = 100.0
                it.performInit()
            }
            table.notifyDataChanged()
        }
        btnOrder5.setOnClickListener {
            OptionTableEntry(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                0.0
            )
            var aa: OptionTableEntry = Gson().fromJson("{strikePrice:23.45}")
        }

        tbLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                currentSelectDate = tab?.text.toString()

                val find = expirationList.find { it.expiration == currentSelectDate }
                reflushData(currentSelectDate, find?.flag)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })


    }

    private fun reflushData(expiration: String, flag: String?) {
        lifecycleScope.launch(Dispatchers.IO){
            try {
                val client = OkHttpClient()
                val formBody = FormBody.Builder()
                    .add("stockCode", "AAPL")
//                    .add("direction", "null")
                    .add("flag", flag?:"")
                    .add("expiration", expiration)
                    .build()

                val request = Request.Builder()
                    .url("http://test.ctfex.pro:8888/app/option/chain/query")
                    .post(formBody)
                    .build()

                val execute = client.newCall(request).execute()
                val bodyStr = execute.body?.string() ?: ""
                var res: HttpResponse<List<OptionTableEntry>> = Gson().fromJson(bodyStr)
                res.body.forEach {
                    it.performInit()
                }
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "$bodyStr", Toast.LENGTH_LONG).show()
                    Log.d("net", bodyStr)
                    (table as SmartTable<OptionTableEntry>).let { v ->
                        v.setData(mutableListOf<OptionTableEntry?>().apply {
                            addAll(res.body)
                            addAll(res.body)
                            addAll(res.body)
                        })
                        v.tableData?.apply {
                            //标题文本同步偏移参考 内容文字偏移
                            titleDrawFormat = object : TitleDrawFormat() {
                                override fun draw(
                                    c: Canvas?,
                                    column: Column<*>?,
                                    rect: Rect?,
                                    config: TableConfig?
                                ) {
                                    val paint = config!!.paint
                                    val isDrawBg = drawBackground(c, column, rect, config)
                                    config!!.columnTitleStyle.fillPaint(paint)
                                    val backgroundFormat = config!!.columnCellBackgroundFormat

                                    paint.textSize = paint.textSize * config!!.zoom
                                    if (isDrawBg && backgroundFormat.getTextColor(column) != TableConfig.INVALID_COLOR) {
                                        paint.color = backgroundFormat.getTextColor(column)
                                    }
                                    rect!!.left += table.config.textLeftOffset
                                    drawText(c!!, column!!, rect!!, paint)
                                }
                            }

                            //点击事件
                            setOnRowClickListener { _, t, _, row ->
                                Toast.makeText(context, "点击了第 $row 行 $t", Toast.LENGTH_LONG)
                                    .show()
                            }

                            columns.forEach {
                                it.setFormat { t->
                                    if (t is Double){
                                        CheckUtils.getFloatNoMoreThanTwoDigits(t as Double)
                                    }else if (t is BigDecimal){
                                        CheckUtils.getFloatNoMoreThanTwoDigits((t as BigDecimal).toDouble())
                                    }
                                    else{
                                        t?.toString()?:"- -"
                                    }
                                }
                            }
                        }
                    }

                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun requestMovieDetails() {
        // 在协程中发起网络请求
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val client = OkHttpClient()
                val formBody = FormBody.Builder()
                    .add("stockCode", "AAPL.US")
                    .build()
                val request = Request.Builder()
                    .url("http://test.ctfex.pro:8888/app/option/chain/expiration")
                    .post(formBody)
                    .build()

                val execute = client.newCall(request).execute()
                val bodyStr = execute.body?.string() ?: ""
                var res: HttpResponse<OptionExpirationEntry> = Gson().fromJson(bodyStr)
                res.body?.recentOptionChain?.forEach {
                    it.performInit()
                }
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "$bodyStr", Toast.LENGTH_LONG).show()
                    Log.d("net", bodyStr)
                    (table as SmartTable<OptionTableEntry>).let { v ->
                        v.setData(res.body.recentOptionChain)
                        v.tableData?.apply {
                            isShowCount = true
                            //标题文本同步偏移参考 内容文字偏移
                            titleDrawFormat = object : TitleDrawFormat() {
                                override fun draw(
                                    c: Canvas?,
                                    column: Column<*>?,
                                    rect: Rect?,
                                    config: TableConfig?
                                ) {
                                    val paint = config!!.paint
                                    val isDrawBg = drawBackground(c, column, rect, config)
                                    config!!.columnTitleStyle.fillPaint(paint)
                                    val backgroundFormat = config!!.columnCellBackgroundFormat

                                    paint.textSize = paint.textSize * config!!.zoom
                                    if (isDrawBg && backgroundFormat.getTextColor(column) != TableConfig.INVALID_COLOR) {
                                        paint.color = backgroundFormat.getTextColor(column)
                                    }
                                    rect!!.left += table.config.textLeftOffset
                                    drawText(c!!, column!!, rect!!, paint)
                                }
                            }
                            //格式化数据 还是在module里改吧
                            //            columns.forEach { column ->
                            //                column.setFormat {
                            //                    when (column.id) {
                            //                        1 -> "$it -- "
                            //                        else -> "$it"
                            //                    }
                            //                }
                            //            }

                            //点击事件
                            setOnRowClickListener { _, t, _, row ->
                                Toast.makeText(context, "点击了第 $row 行 $t", Toast.LENGTH_LONG)
                                    .show()
                            }
                        }
                    }


//                    初始化tabLayout
                    tbLayout.removeAllTabs()
                    expirationList=res.body.expirationList
                    expirationList.forEach {
                        tbLayout.addTab(tbLayout.newTab().apply {
                            text = it.expiration
                        })
                    }

                }

//                val result = OptionAPI.api().getMovieDetails( "00700.HK")
//                // 在主线程中更新 UI
//                withContext(Dispatchers.Main) {
//                    Toast.makeText(context,"hhh",Toast.LENGTH_LONG).show()
//                }
            } catch (e: Exception) {
                e.printStackTrace()
                // 处理异常
                // ...
            }
        }

    }

    inline fun <reified T> Gson.fromJson(json: String): T =
        fromJson(json, object : TypeToken<T>() {}.type)

//    var data = listOf<OptionTableEntry>(
//        OptionTableEntry(
//            23.34, "fxxx", 3.423, 23, 333.33, 444, 444.4, BigDecimal(44.44), 33.44, 1000,
//            BigDecimal(33.3), 22.22, 100, 22.2, 22.3, 22.45, 11.11, 22.22, 33.33, 44.44, 55.55
//        ),
//        OptionTableEntry(
//            2.34, "fxxx", 3.423, 23, 333.33, 444, 444.4, BigDecimal(44.44), 33.44, 1000,
//            BigDecimal(33.3), 22.22, 100, 22.2, 22.3, 22.45, 11.11, 22.22, 33.33, 44.44, 55.55
//        ),
//        OptionTableEntry(
//            23.34, "fxxx", 3.423, 23, 333.33, 444, 444.4, BigDecimal(44.44), 33.44, 1000,
//            BigDecimal(33.3), 22.22, 100, 22.2, 22.3, 22.45, 11.11, 22.22, 33.33, 44.44, 55.55
//        ),
//        OptionTableEntry(
//            23.34, "fxxx", 3.423, 23, 333.33, 444, 444.4, BigDecimal(44.44), 33.44, 1000,
//            BigDecimal(33.3), 22.22, 100, 22.2, 22.3, 22.45, 11.11, 22.22, 33.33, 44.44, 55.55
//        ),
//        OptionTableEntry(
//            23.34, "fxxx", 3.423, 23, 333.33, 444, 444.4, BigDecimal(44.44), 33.44, 1000,
//            BigDecimal(33.3), 22.22, 100, 22.2, 22.3, 22.45, 11.11, 22.22, 33.33, 44.44, 55.55
//        ),
//        OptionTableEntry(
//            23.34, "fxxx", 3.423, 23, 333.33, 444, 444.4, BigDecimal(44.44), 33.44, 1000,
//            BigDecimal(33.3), 22.22, 100, 22.2, 22.3, 22.45, 11.11, 22.22, 33.33, 44.44, 55.55
//        ),
//        OptionTableEntry(
//            23.34, "fxxx", 3.423, 23, 333.33, 444, 444.4, BigDecimal(44.44), 33.44, 1000,
//            BigDecimal(33.3), 22.22, 100, 22.2, 22.3, 22.45, 11.11, 22.22, 33.33, 44.44, 55.55
//        ),
//        OptionTableEntry(
//            23.34, "fxxx", 3.423, 23, 333.33, 444, 444.4, BigDecimal(44.44), 33.44, 1000,
//            BigDecimal(33.3), 22.22, 100, 22.2, 22.3, 22.45, 11.11, 22.22, 33.33, 44.44, 55.55
//        ),
//        OptionTableEntry(
//            23.34, "fxxx", 3.423, 23, 333.33, 444, 444.4, BigDecimal(44.44), 33.44, 1000,
//            BigDecimal(33.3), 22.22, 100, 22.2, 22.3, 22.45, 11.11, 22.22, 33.33, 44.44, 55.55
//        ),
//        OptionTableEntry(
//            23.34, "fxxx", 3.423, 23, 333.33, 444, 444.4, BigDecimal(44.44), 33.44, 1000,
//            BigDecimal(33.3), 22.22, 100, 22.2, 22.3, 22.45, 11.11, 22.22, 33.33, 44.44, 55.55
//        ),
//        OptionTableEntry(
//            23.34, "fxxx", 3.423, 23, 333.33, 444, 444.4, BigDecimal(44.44), 33.44, 1000,
//            BigDecimal(33.3), 22.22, 100, 22.2, 22.3, 22.45, 11.11, 22.22, 33.33, 44.44, 55.55
//        ),
//        OptionTableEntry(
//            23.34, "fxxx", 3.423, 23, 333.33, 444, 444.4, BigDecimal(44.44), 33.44, 1000,
//            BigDecimal(33.3), 22.22, 100, 22.2, 22.3, 22.45, 11.11, 22.22, 33.33, 44.44, 55.55
//        ),
//    )
}