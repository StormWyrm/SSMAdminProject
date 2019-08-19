package com.github.stormwyrm.ssmadmin.controller

import com.github.stormwyrm.ssmadmin.domain.Role
import com.github.stormwyrm.ssmadmin.service.IRoleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("/role")
 class RoleController {

    @Autowired
    private lateinit var roleService: IRoleService

    @RequestMapping("/findAll")
    fun findAll(): ModelAndView = ModelAndView().apply {
        addObject("roleList", roleService.findAll())
        viewName = "role-list"
    }

    @RequestMapping("/findById")
    fun findById(@RequestParam(name = "id", required = true) id: String): ModelAndView {
        return ModelAndView().apply {
            addObject("role", roleService.findById(id))
            viewName = "role-show"
        }
    }

    @RequestMapping("/findByPermissionId")
    fun findByPermissionId(@RequestParam(name = "id", required = true) id: String): ModelAndView {
        return ModelAndView().apply {
            addObject("role", roleService.findByPermissionId(id))
            viewName = "role-show"
        }
    }

    @RequestMapping("/save")
    fun save(role: Role): String {
        roleService.save(role)
        return "redirect:findAll"
    }

    @RequestMapping("/deleteRoleById")
    fun deleteRoleById(@RequestParam(name = "id", required = true) id: String): String {
        roleService.deleteRoleById(id)
        return "redirect:findAll"
    }


}