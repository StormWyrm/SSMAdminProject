package com.github.stormwyrm.ssmadmin.service.impl

import com.github.stormwyrm.ssmadmin.dao.IPermissionDao
import com.github.stormwyrm.ssmadmin.domain.Permission
import com.github.stormwyrm.ssmadmin.service.IPermissionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service("permissionService")
class PermissionServiceImpl : IPermissionService {
    @Autowired
    private lateinit var permissionDao: IPermissionDao

    override fun findAll(): List<Permission> {
        return permissionDao.findAll()
    }

    override fun findById(id: String): Permission {
        return permissionDao.findByPermissionId(id)
    }

    override fun save(permission: Permission) {
        permissionDao.save(permission)
    }

}