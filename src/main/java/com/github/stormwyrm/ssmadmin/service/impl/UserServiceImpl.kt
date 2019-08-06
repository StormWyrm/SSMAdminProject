package com.github.stormwyrm.ssmadmin.service.impl

import com.github.stormwyrm.ssmadmin.dao.IUserDao
import com.github.stormwyrm.ssmadmin.domain.Role
import com.github.stormwyrm.ssmadmin.domain.UserInfo
import com.github.stormwyrm.ssmadmin.service.IUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service("userService")
class UserServiceImpl : IUserService {

    @Autowired
    private lateinit var userDao: IUserDao

    @Autowired
    private lateinit var passwordEncoder : BCryptPasswordEncoder

    override fun findById(id: String): UserInfo? {
        return userDao.findById(id)
    }

    override fun save(userInfo: UserInfo) {
        userInfo.id = UUID.randomUUID().toString().substring(0,32).toUpperCase()
        //对密码进行加密处理
        userInfo.password = passwordEncoder.encode(userInfo.password)
        userDao.save(userInfo)
    }

    override fun findAll(): List<UserInfo> {
        return userDao.findAll()
    }

    override fun loadUserByUsername(username: String): UserDetails? {
        val userInfo = userDao.findByUsername(username)
        return userInfo?.run {
            User(username, password, status != 0, true, true, true, getAuthority(roles))
        }
    }

    //作用就是返回一个List集合，集合中装入的是角色描述
    private fun getAuthority(roles: List<Role>?): List<SimpleGrantedAuthority>? {
        return roles?.let {
            val list = ArrayList<SimpleGrantedAuthority>()
            for (role in it) {
                list.add(SimpleGrantedAuthority("ROLE_${role.roleName}"))
            }
            list
        }

    }
}
