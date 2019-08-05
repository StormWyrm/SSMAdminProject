package com.github.stormwyrm.ssmadmin.domain

//与数据库中users对应
class UserInfo {
    var id: String? = null
    var username: String? = null
    var email: String? = null
    var password: String? = null
    var phoneNum: String? = null
    var status: Int = 0
    //状态0 未开启 1 开启
    var statusStr: String? = null
        get() {
            if (status == 0) {
                this.statusStr = "未开启"
            } else if (status == 1) {
                this.statusStr = "开启"
            }
            return field
        }
    var roles: List<Role>? = null
}
