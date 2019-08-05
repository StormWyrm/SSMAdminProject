package com.github.stormwyrm.ssmadmin.domain

//旅客
class Traveller {
    var id: String? = null
    var name: String? = null
    var sex: String? = null
    var phoneNum: String? = null
    var credentialsType: Int? = null
    val credentialsTypeStr: String?
        get() = credentialsType?.run {
            when (this) {
                0 -> "身份证"
                1 -> "护照"
                2 -> "军官证"
                else -> "未知"
            }
        }

    var credentialsNum: String? = null
    var travellerType: Int? = null
    val travellerTypeStr: String?
        get() = travellerType?.run {
            when (this) {
                0 -> "成人"
                1 -> "儿童"
                else -> "未知"
            }
        }
}
