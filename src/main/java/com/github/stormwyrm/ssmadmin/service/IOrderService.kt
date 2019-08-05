package com.github.stormwyrm.ssmadmin.service

import com.github.stormwyrm.ssmadmin.domain.Order

interface IOrderService {
    fun findAll(page: Int, size: Int): List<Order>

    fun findById(orderId: String): Order
}
