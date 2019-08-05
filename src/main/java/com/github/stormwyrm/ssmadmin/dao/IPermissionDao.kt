package com.github.stormwyrm.ssmadmin.dao

import com.github.stormwyrm.ssmadmin.domain.Permission
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Select

interface IPermissionDao {
    @Select("SELECT * FROM permission WHERE id IN (SELECT permissionId FROM role_permission WHERE roleId=#{id} )")
    fun findByRoleId(id: String): List<Permission>

    @Select("SELECT * FROM permission")
    fun findAll(): List<Permission>

    @Insert("INSERT INTO permission (permissionName,url) VALUES(#{permissionName},#{url})")
    fun save(permission: Permission)
}