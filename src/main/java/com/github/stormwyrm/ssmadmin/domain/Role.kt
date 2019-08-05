package com.github.stormwyrm.ssmadmin.domain

class Role {
    var id: String? = null
    var roleName: String? = null
    var roleDesc: String? = null
    var permissions: List<Permission>? = null
    var userInfos: List<UserInfo>? = null
}
