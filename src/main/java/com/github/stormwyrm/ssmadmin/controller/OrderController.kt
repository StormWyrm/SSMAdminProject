package com.github.stormwyrm.ssmadmin.controller

import com.github.pagehelper.PageInfo
import com.github.stormwyrm.ssmadmin.service.IOrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Required
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("/order")
class OrderController {

    @Autowired
    private lateinit var orderService: IOrderService

    @RequestMapping("/findAll")
    fun findAll(@RequestParam(name = "page", required = true, defaultValue = "1") page: Int,
                @RequestParam(name = "size", required = true, defaultValue = "4") size: Int)
            : ModelAndView = ModelAndView().apply {

        val orders = orderService.findAll(page,size)
        val pageInfo = PageInfo(orders)
        addObject("pageInfo", pageInfo)
        viewName = "orders-page-list"
    }

    @RequestMapping("findById")
    fun findById(@RequestParam(name = "id", required = true) orderId: String) = ModelAndView().apply {
        val order = orderService.findById(orderId)
        addObject("orders", order)
        viewName = "orders-show"
    }
}