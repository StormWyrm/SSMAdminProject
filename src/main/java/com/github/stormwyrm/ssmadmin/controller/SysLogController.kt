package com.github.stormwyrm.ssmadmin.controller

import com.github.stormwyrm.ssmadmin.service.ILogService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("/sysLog")
class SysLogController {

    @Autowired
    private lateinit var logService: ILogService

    @RequestMapping("/findAll")
    @Throws(Exception::class)
    fun findAll(): ModelAndView {
        val mv = ModelAndView()
        val sysLogList = logService.findAll()
        mv.addObject("sysLogs", sysLogList)
        mv.viewName = "syslog-list"
        return mv
    }
}
