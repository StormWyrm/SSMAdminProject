package com.github.stormwyrm.ssmadmin.dao

import com.github.stormwyrm.ssmadmin.domain.Permission
import org.apache.ibatis.annotations.*
import org.springframework.stereotype.Repository

@Repository("permissionDao")
interface IPermissionDao {
    @Select("SELECT * FROM permission WHERE id IN (SELECT permissionId FROM role_permission WHERE roleId=#{id} )")
    fun findByRoleId(id: String): List<Permission>

    @Select("SELECT * FROM permission WHERE id = #{permissionId}")
    @Results(
            Result(column = "id", property = "id", id = true),
            Result(column = "permissionName", property = "permissionName"),
            Result(column = "url", property = "url"),
            Result(column = "roles", property = "id", javaType = List::class, many = Many(select = "com.github.stormwyrm.ssmadmin.dao.IRoleDao.findByPermissionId"))
    )
    fun findByPermissionId(permissionId: String): Permission

    @Select("SELECT * FROM permission")
    fun findAll(): List<Permission>

    @Insert("INSERT INTO permission VALUES(#{id},#{permissionName},#{url})")
    fun save(permission: Permission)

    @Select("SELECT COUNT(*) FROM permission")
    fun getCount(): Long

    @Select("DELETE FROM permission WHERE id = #{id}")
    fun delete(id: String)

}