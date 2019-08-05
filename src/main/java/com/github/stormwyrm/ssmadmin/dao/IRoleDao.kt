package com.github.stormwyrm.ssmadmin.dao

import com.github.stormwyrm.ssmadmin.domain.Role
import org.apache.ibatis.annotations.*

interface IRoleDao {

    @Select("SELECT * FROM role WHERE id IN (SELECT roleId FROM user_role WHERE userId = #{userId})")
    @Results(
            Result(id = true, property = "id", column = "id"),
            Result(property = "roleName", column = "roleName"),
            Result(property = "roleDesc", column = "roleDesc"),
            Result(property = "permissions", column = "id", javaType = List::class, many = Many(select = "com.github.stormwyrm.ssmadmin.dao.IPermissionDao.findByRoleId")))
    fun findByUserId(userId: String): List<Role>

    @Select("SELECT * FROM role")
    @Throws(Exception::class)
    fun findAll(): List<Role>

    @Insert("INSERT INTO role (roleName,roleDesc) VALUES(#{roleName},#{roleDesc})")
    fun save(role: Role)
}
