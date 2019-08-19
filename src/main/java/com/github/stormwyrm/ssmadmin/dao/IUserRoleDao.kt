package com.itheima.ssm.dao

import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Param

interface IUserRoleDao {
    @Insert("INSERT INTO users_role VALUES(#{userId},#{roleId})")
    fun save(@Param("userId") userId: String, @Param("roleId") roleId: String)

    @Delete("DELETE FROM users_role WHERE roleId=#{roleId}")
    fun deletByRoleId(roleId: String)

    @Delete("DELETE FROM users_role WHERE userId=#{userId}")
    fun deletByUserId(roleId: String)
}