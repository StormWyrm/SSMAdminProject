package com.github.stormwyrm.ssmadmin.dao

import com.github.stormwyrm.ssmadmin.domain.UserInfo
import org.apache.ibatis.annotations.*

interface IUserDao {

    @Select("SELECT * FROM user WHERE username=#{username}")
    @Results(Result(id = true, property = "id", column = "id"),
            Result(property = "username", column = "username"),
            Result(property = "email", column = "email"),
            Result(property = "password", column = "password"),
            Result(property = "phoneNum", column = "phoneNum"),
            Result(property = "status", column = "status"),
            Result(property = "roles", column = "id", javaType = List::class, many = Many(select = "com.github.stormwyrm.ssmadmin.dao.IRoleDao.findByUserId")))
    fun findByUsername(username: String): UserInfo?

    @Select("SELECT * FROM user WHERE id=#{id}")
    @Results(
            Result(id = true, property = "id", column = "id"),
            Result(property = "username", column = "username"),
            Result(property = "email", column = "email"),
            Result(property = "password", column = "password"),
            Result(property = "phoneNum", column = "phoneNum"),
            Result(property = "status", column = "status"),
            Result(property = "roles", column = "id", javaType = List::class, many = Many(select = "com.github.stormwyrm.ssmadmin.dao.IRoleDao.findByUserId")))
    fun findById(id: String): UserInfo?

    @Select("SELECT * FROM user WHERE id IN (SELECT * FROM user_role WHERE roleId = #{roleId})")
    @Results(
            Result(id = true, property = "id", column = "id"),
            Result(property = "username", column = "username"),
            Result(property = "email", column = "email"),
            Result(property = "password", column = "password"),
            Result(property = "phoneNum", column = "phoneNum"),
            Result(property = "status", column = "status"),
            Result(property = "roles", column = "id", javaType = List::class, many = Many(select = "com.github.stormwyrm.ssmadmin.dao.IRoleDao.findByUserId")))
    fun findByRoleId(roleId: String): List<UserInfo>?


    @Select("SELECT * FROM user")
    fun findAll(): List<UserInfo>

    @Insert("INSERT INTO user VALUES(#{id},#{email},#{username},#{password},#{phoneNum},#{status})")
    fun save(userInfo: UserInfo)
}
