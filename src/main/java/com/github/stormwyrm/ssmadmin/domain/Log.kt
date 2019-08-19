package com.github.stormwyrm.ssmadmin.domain

import com.github.stormwyrm.ssmadmin.util.DateUtils
import org.springframework.format.annotation.DateTimeFormat
import java.util.*

class Log {
    var id: String? = null
    @DateTimeFormat
    var visitTime: Date? = null
    var visitTimeStr: String? = null
        get() {
            visitTime?.let {
                this.visitTimeStr = DateUtils.date2String(it, "yyyy-MM-dd HH:mm")
            }
            return field
        }
    var username: String? = null
    var ip: String? = null
    var url: String? = null
    var executionTime: Long? = null
    var method: String? = null
}