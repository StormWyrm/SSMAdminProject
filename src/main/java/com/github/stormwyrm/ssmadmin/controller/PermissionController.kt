package com.github.stormwyrm.ssmadmin.controller

import com.github.stormwyrm.ssmadmin.domain.Permission
import com.github.stormwyrm.ssmadmin.service.IPermissionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("/permission")
 class PermissionController{

    @Autowired
    private lateinit var permissionService : IPermissionService

    @RequestMapping("/findAll")
    fun findAll() : ModelAndView = ModelAndView().apply {
        addObject("permissionList", permissionService.findAll())
        viewName = "permission-list"
    }

    @RequestMapping("/findById")
    fun findById(id: String): ModelAndView {
        val permission = permissionService.findById(id)
        val mv = ModelAndView()
        mv.viewName = "permission-show"
        mv.addObject("permission", permission)
        return mv
    }

    @RequestMapping("/deletePermission")
    fun deletePermission(id : String) : String{
        permissionService.delete(id)
        return "redirect:findAll"
    }

    @RequestMapping("/save")
    fun save(permission : Permission) : String{
        permissionService.save(permission)
        return "redirect:findAll"
    }
}