package com.github.stormwyrm.ssmadmin.service.impl

import com.github.stormwyrm.ssmadmin.dao.IPermissionDao
import com.github.stormwyrm.ssmadmin.dao.IRolePermissioinDao
import com.github.stormwyrm.ssmadmin.domain.Permission
import com.github.stormwyrm.ssmadmin.service.IPermissionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service("permissionService")
class PermissionServiceImpl : IPermissionService {

    @Autowired
    private lateinit var rolePermissionDao : IRolePermissioinDao

    @Autowired
    private lateinit var permissionDao: IPermissionDao


    override fun findAll(): List<Permission> {
        return permissionDao.findAll()
    }

    override fun findById(id: String): Permission {
        return permissionDao.findByPermissionId(id)
    }

    override fun delete(id: String) {
        rolePermissionDao.deleteByPermissionId(id)
        permissionDao.delete(id)
    }

    override fun save(permission: Permission) {
        permission.id = "permission${permissionDao.getCount()+1}"
        permissionDao.save(permission)
    }

}