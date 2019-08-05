package com.github.stormwyrm.ssmadmin.domain

import com.github.stormwyrm.ssmadmin.util.DateUtils
import org.springframework.format.annotation.DateTimeFormat
import java.util.*

//订单
class Order {
    var id: String? = null
    var orderNum: String? = null
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    var orderTime: Date? = null
    var orderTimeStr: String? = null
        get() {
            orderTime?.let {
                this.orderTimeStr = DateUtils.date2String(it, "yyyy-MM-dd HH:mm")
            }
            return field
        }
    var orderStatus: Int = 0
    //订单状态(0 未支付 1 已支付)
    var orderStatusStr: String? = null
        get() {
            if (orderStatus == 0) {
                this.orderStatusStr = "未支付"
            } else if (orderStatus == 1) {
                this.orderStatusStr = "已支付"
            }
            return field
        }
    var peopleCount: Int = 0
    var product: Product? = null
    var travellers: List<Traveller>? = null
    var member: Member? = null
    var payType: Int? = null
    //支付方式(0 支付宝 1 微信 2其它)
    var payTypeStr: String? = null
        get() {
            if (payType == 0) {
                this.payTypeStr = "支付宝"
            } else if (payType == 1) {
                this.payTypeStr = "微信"
            } else if (payType == 2) {
                this.payTypeStr = "其它"
            }
            return field
        }
    var orderDesc: String? = null
}
