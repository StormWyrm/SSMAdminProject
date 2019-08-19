package com.github.stormwyrm.ssmadmin.controller

import com.github.stormwyrm.ssmadmin.domain.Product
import com.github.stormwyrm.ssmadmin.service.IProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import java.util.*

@Controller
@RequestMapping("/product")
 class ProductController {
    @Autowired
    private lateinit var productService: IProductService

    @RequestMapping("/findAll")
    fun findAll(): ModelAndView = ModelAndView().apply {
        val products = productService.findAll()
        addObject("productList", products)
        viewName = "product-list1"
    }

    @RequestMapping("/save")
    fun add(product: Product): String {
        product.id = UUID.randomUUID().toString().substring(0,32).toUpperCase()
        productService.add(product)
        return "redirect:findAll"
    }
}