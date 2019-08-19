package com.github.stormwyrm.ssmadmin.service.impl

import com.github.stormwyrm.ssmadmin.dao.IRoleDao
import com.github.stormwyrm.ssmadmin.domain.Role
import com.github.stormwyrm.ssmadmin.service.IRoleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service("roleService")
class RoleServiceImpl : IRoleService {
    @Autowired
    private lateinit var roleDao: IRoleDao

    override fun findAll(): List<Role> {
        return roleDao.findAll()
    }

    override fun findById(id: String) : Role {
        return roleDao.findByRoleId(id)
    }

    override fun findByPermissionId(id: String): List<Role> {
        return roleDao.findByPermissionId(id)
    }

    override fun save(role: Role) {
        role.id = "role${roleDao.getCount()+1}"
        return roleDao.save(role)
    }

    override fun deleteRoleById(id: String) {
        roleDao.deleteRoleById(id)
    }

}