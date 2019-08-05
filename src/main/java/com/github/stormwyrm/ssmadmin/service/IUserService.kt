package com.github.stormwyrm.ssmadmin.service

import com.github.stormwyrm.ssmadmin.domain.UserInfo
import org.springframework.security.core.userdetails.UserDetailsService

interface IUserService : UserDetailsService {

    fun findAll(): List<UserInfo>

    fun save(userInfoInfo: UserInfo)

    fun findById(id: String): UserInfo?
}