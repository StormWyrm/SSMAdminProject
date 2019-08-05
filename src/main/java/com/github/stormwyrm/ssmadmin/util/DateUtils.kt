package com.github.stormwyrm.ssmadmin.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

object DateUtils {

    //日期转换成字符串
    fun date2String(date: Date, patt: String): String {
        val sdf = SimpleDateFormat(patt)
        val format = sdf.format(date)
        return format
    }

    //字符串转换成日期
    @Throws(ParseException::class)
    fun string2Date(str: String, patt: String): Date {
        val sdf = SimpleDateFormat(patt)
        val parse = sdf.parse(str)
        return parse
    }
}
