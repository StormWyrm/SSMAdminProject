package com.github.stormwyrm.ssmadmin.dao

import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Repository

@Repository("rolePermissionDao")
interface IRolePermissioinDao {

    @Delete("DELETE FROM role_permission WHERE permissionId=#{id}")
    fun deleteByPermissionId(id: String)

    @Delete("DELETE FROM role_permission WHERE roleId=#{id}")
    fun deleteByRoleId(id: String)

    @Insert("INSERT INTO role_permission VALUES(#{roleId},#{permissionId})")
    fun save(@Param("roleId") roleId: String, @Param("permissionId") permissionId: String)
}


