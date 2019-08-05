package com.github.stormwyrm.ssmadmin.service.impl

import com.github.stormwyrm.ssmadmin.dao.IProductDao
import com.github.stormwyrm.ssmadmin.domain.Product
import com.github.stormwyrm.ssmadmin.service.IProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service("productService")
class ProductServiceImpl : IProductService {

    @Autowired
    private lateinit var productDao: IProductDao

    override fun findAll(): List<Product> = productDao.findAll()

    override fun add(product: Product) = productDao.save(product)
}
