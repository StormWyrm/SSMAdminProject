package com.github.stormwyrm.ssmadmin.domain

import com.github.stormwyrm.ssmadmin.util.DateUtils
import org.springframework.format.annotation.DateTimeFormat
import java.util.*

/**
 * 产品信息
 */
class Product {
    var id: String? = null // 主键
    var productNum: String? = null // 编号 唯一
    var productName: String? = null // 名称
    var cityName: String? = null // 出发城市
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    var departureTime: Date? = null // 出发时间
    var departureTimeStr: String? = null
        get() {
            departureTime?.let {
                this.departureTimeStr = DateUtils.date2String(it, "yyyy-MM-dd HH:mm:ss")
            }
            return field
        }
    var productPrice: Double = 0.toDouble() // 产品价格
    var productDesc: String? = null // 产品描述
    var productStatus: Int? = null // 状态 0 关闭 1 开启
    // 状态 0 关闭 1 开启
    var productStatusStr: String? = null
        get() {
            if (productStatus != null) {
                if (productStatus == 0)
                    this.productStatusStr = "关闭"
                if (productStatus == 1)
                    this.productStatusStr = "开启"
            }
            return field
        }
}
