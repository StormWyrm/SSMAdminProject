package com.github.stormwyrm.ssmadmin.service.impl

import com.github.pagehelper.PageHelper
import com.github.stormwyrm.ssmadmin.dao.IOrderDao
import com.github.stormwyrm.ssmadmin.domain.Order
import com.github.stormwyrm.ssmadmin.service.IOrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service("orderService")
class OrderServiceImpl : IOrderService {
    @Autowired
    private lateinit var orderDao: IOrderDao

    override fun findAll(page: Int, size: Int): List<Order> {
        //参数pageNum 是页码值   参数pageSize 代表是每页显示条数
        PageHelper.startPage<Any>(page, size)
        return orderDao.findAll()
    }

    override fun findById(orderId: String): Order = orderDao.findById(orderId)

}