package com.github.stormwyrm.ssmadmin.service

import com.github.stormwyrm.ssmadmin.domain.Product

interface IProductService {
    fun findAll(): List<Product>

    fun add(product: Product)
}