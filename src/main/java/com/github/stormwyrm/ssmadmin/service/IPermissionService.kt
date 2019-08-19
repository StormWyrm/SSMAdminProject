package com.github.stormwyrm.ssmadmin.service

import com.github.stormwyrm.ssmadmin.domain.Permission

interface IPermissionService {
    fun findAll(): List<Permission>

    fun save(permission: Permission)

    fun findById(id: String): Permission

    fun delete(id : String)
}