package com.github.stormwyrm.ssmadmin.service.impl

import com.github.stormwyrm.ssmadmin.dao.IUserDao
import com.github.stormwyrm.ssmadmin.domain.Role
import com.github.stormwyrm.ssmadmin.domain.UserInfo
import com.github.stormwyrm.ssmadmin.service.IUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.ArrayList

@Service("userService")
class UserServiceImpl : IUserService {

    @Autowired
    private lateinit var userDao: IUserDao

    override fun findById(id: String): UserInfo? {
        return userDao.findById(id)
    }

    override fun save(UserInfo: UserInfo) {
        //对密码进行加密处理
        userDao.save(UserInfo)
    }

    override fun findAll(): List<UserInfo> {
        return userDao.findAll()
    }

    override fun loadUserByUsername(username: String): UserDetails? {
        val userInfo = userDao.findByUsername(username)
        return userInfo?.run {
            User(username, "{noop}$password", status != 0, true, true, true, getAuthority(roles))
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
