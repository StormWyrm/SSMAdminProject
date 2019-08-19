package com.github.stormwyrm.ssmadmin.dao

import com.github.stormwyrm.ssmadmin.domain.Role
import org.apache.ibatis.annotations.*
import org.springframework.stereotype.Repository

@Repository("roleDao")
interface IRoleDao {

    @Select("SELECT * FROM role WHERE id IN (SELECT roleId FROM user_role WHERE userId = #{userId})")
    @Results(
            Result(id = true, property = "id", column = "id"),
            Result(property = "roleName", column = "roleName"),
            Result(property = "roleDesc", column = "roleDesc"),
            Result(property = "permissions", column = "id", javaType = List::class, many = Many(select = "com.github.stormwyrm.ssmadmin.dao.IPermissionDao.findByRoleId")))
    fun findByUserId(userId: String): List<Role>

    @Select("SELECT * FROM role WHERE id IN (SELECT roleId FROM role_permission WHERE permissionId = #{permissionId})")
    @Results(
            Result(id = true, property = "id", column = "id"),
            Result(property = "roleName", column = "roleName"),
            Result(property = "roleDesc", column = "roleDesc"),
            Result(property = "userInfos",column = "id",many = Many(select = "com.github.stormwyrm.ssmadmin.dao.IRoleDao")))
    fun findByPermissionId(permissionId : String) : List<Role>


    @Select("SELECT * FROM role WHERE id = #{roleId}")
    @Results(
            Result(id = true, property = "id", column = "id"),
            Result(property = "roleName", column = "roleName"),
            Result(property = "roleDesc", column = "roleDesc"),
            Result(property = "permissions", column = "id", javaType = List::class, many = Many(select = "com.github.stormwyrm.ssmadmin.dao.IPermissionDao.findByRoleId")))
    fun findByRoleId(roleId : String) : Role

    @Select("SELECT * FROM role")
    @Throws(Exception::class)
    fun findAll(): List<Role>

    @Insert("INSERT INTO role VALUES(#{id},#{roleName},#{roleDesc})")
    fun save(role: Role)

    @Select("SELECT COUNT(*) FROM role")
    fun getCount() : Long

    @Select("DELETE FROM role WHERE id=#{roleId}")
    fun deleteRoleById(roleId: String)
}
