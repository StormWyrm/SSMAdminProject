package com.github.stormwyrm.ssmadmin.controller

import com.github.stormwyrm.ssmadmin.domain.UserInfo
import com.github.stormwyrm.ssmadmin.service.IUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("/user")
class UserController {
    @Autowired
    private lateinit var userService : IUserService

    @RequestMapping("/findAll")
    fun findAll(): ModelAndView = ModelAndView().apply {
        val userList = userService.findAll()
        addObject("userList", userList)
        viewName = "user-list"
    }

    @RequestMapping("/findById")
    fun findById(@RequestParam(name = "id",required = true) id : String): ModelAndView = ModelAndView().apply {
        val userInfo = userService.findById(id)
        addObject("user", userInfo)
        viewName = "user-show1"
    }

    //用户添加
    @RequestMapping("/save")
    fun save(userInfo: UserInfo): String {
        userService.save(userInfo)
        return "redirect:findAll"
    }
}