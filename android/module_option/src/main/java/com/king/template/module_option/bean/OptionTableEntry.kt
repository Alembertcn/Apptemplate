package com.king.template.module_option.bean

import android.graphics.Paint
import android.util.Log
import com.bin.david.form.annotation.SmartColumn
import com.bin.david.form.annotation.SmartTable
import com.king.template.utils.CheckUtils
import java.math.BigDecimal
import java.util.Collections.max

@SmartTable(name="表名")
class OptionTableEntry (
    // id为该字段所在表格排序位置
    @SmartColumn(id =1,name = "行权价", fixed = true, autoMerge = true,maxMergeCount=2, align = Paint.Align.LEFT ,titleAlign = Paint.Align.LEFT)
    var strikePrice: Double?,
    @SmartColumn(id =2,name = "方向", align = Paint.Align.LEFT , titleAlign = Paint.Align.LEFT)
    var direction: String?,

    var askPrice: Double?,
    var askVol: Int?,//买盘数量
    var bidPrice:Double?,
    var bidVol:Int?,//卖盘数量

    @SmartColumn(id =5,name = "最新价", align = Paint.Align.LEFT, titleAlign = Paint.Align.LEFT)
    var last: Double?,

    @SmartColumn(id =6,name = "涨跌额", align = Paint.Align.LEFT, titleAlign = Paint.Align.LEFT)
    var change: BigDecimal?,

    @SmartColumn(id =7,name = "涨跌幅", align = Paint.Align.LEFT, titleAlign = Paint.Align.LEFT)
    var changeRate: Double?,

    @SmartColumn(id =8,name = "成交量", align = Paint.Align.LEFT, titleAlign = Paint.Align.LEFT)
    var volume:Long?,

    @SmartColumn(id =9,name = "成交额", align = Paint.Align.LEFT, titleAlign = Paint.Align.LEFT)
    var amount: BigDecimal?,
    @SmartColumn(id =10,name = "溢价", align = Paint.Align.LEFT, titleAlign = Paint.Align.LEFT)
    var premium: Double?,

    @SmartColumn(id =11,name = "未平仓数", align = Paint.Align.LEFT, titleAlign = Paint.Align.LEFT)
    var position: Int?,
    @SmartColumn(id =12,name = "隐含波动率", align = Paint.Align.LEFT, titleAlign = Paint.Align.LEFT)
    var iv: Double?,
    @SmartColumn(id =13,name = "Delta", align = Paint.Align.LEFT, titleAlign = Paint.Align.LEFT)
    var delta: Double?,
    @SmartColumn(id =14,name = "Gamma", align = Paint.Align.LEFT, titleAlign = Paint.Align.LEFT)
    var gamma: Double?,
    @SmartColumn(id =15,name = "Vega", align = Paint.Align.LEFT, titleAlign = Paint.Align.LEFT)
    var vega: Double?,
    @SmartColumn(id =16,name = "Theta", align = Paint.Align.LEFT, titleAlign = Paint.Align.LEFT)
    var theta: Double?,
    //    下面这是那个没有
    var Rho: Double?,
    var currentPrice:Double=0.0
){
    @SmartColumn(id =3,name = "买盘", align = Paint.Align.LEFT, titleAlign = Paint.Align.LEFT)
    var askPriceStr:String="- -"

    @SmartColumn(id =4,name = "卖盘", align = Paint.Align.LEFT, titleAlign = Paint.Align.LEFT)
    var bidPriceStr:String="- -"
    //    下面这是那个没有
    @SmartColumn(id =17,name = "Rho", align = Paint.Align.LEFT, titleAlign = Paint.Align.LEFT)
    var RhoStr: String = "- -"
    @SmartColumn(id =18,name = "内在价值", align = Paint.Align.LEFT, titleAlign = Paint.Align.LEFT)
    var nzjzStr:String = "- -"

    @SmartColumn(id =19,name = "时间价值", align = Paint.Align.LEFT, titleAlign = Paint.Align.LEFT)
    var sjjzStr: String= (last?:0.0 - kotlin.math.max(0.0,(if(direction.equals("call", ignoreCase = false)) 1 else -1) * (currentPrice * 1.1 - (strikePrice ?: 0.0)))).toString()

    fun performInit() {
        askPriceStr="${if(askPrice == null) "- -" else "$askPrice\n"}x$askVol"
        bidPriceStr=" ${if(bidPrice == null) "- -" else "$bidPrice\n"}x$bidVol"
        sjjzStr = if(last == null || currentPrice ==null || direction == null || strikePrice ==null) "- -" else  (last?:0.0 - kotlin.math.max(0.0,(if(direction.equals("call", ignoreCase = false)) 1 else -1) * (currentPrice * 1.1 - (strikePrice ?: 0.0)))).toString()
        nzjzStr =if( currentPrice ==null || direction == null || strikePrice ==null) "- -" else  CheckUtils.getFloatNoMoreThanTwoDigits(kotlin.math.max(0.0,(if(direction.equals("call", ignoreCase = false)) 1 else -1) * (currentPrice * 1.1 - (strikePrice ?: 0.0))))
        RhoStr = if(Rho == null)"- -" else Rho.toString()
    }

}