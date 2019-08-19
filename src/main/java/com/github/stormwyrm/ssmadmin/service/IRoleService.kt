package com.github.stormwyrm.ssmadmin.service

import com.github.stormwyrm.ssmadmin.domain.Role

interface IRoleService {

    fun findAll(): List<Role>

    fun save(role: Role)

    fun findById(id: String) : Role

    fun findByPermissionId(id : String) : List<Role>

    fun deleteRoleById(id: String)
}