package com.github.stormwyrm.ssmadmin.dao

import com.github.stormwyrm.ssmadmin.domain.Member
import com.github.stormwyrm.ssmadmin.domain.Order
import com.github.stormwyrm.ssmadmin.domain.Product
import org.apache.ibatis.annotations.*
import org.springframework.stereotype.Repository

@Repository("orderDao")
interface IOrderDao {
    @Select("SELECT * FROM orders")
    @Results(
            Result(id = true, property = "id", column = "id"),
            Result(property = "orderNum", column = "orderNum"),
            Result(property = "orderTime", column = "orderTime"),
            Result(property = "orderStatus", column = "orderStatus"),
            Result(property = "peopleCount", column = "peopleCount"),
            Result(property = "peopleCount", column = "peopleCount"),
            Result(property = "payType", column = "payType"),
            Result(property = "orderDesc", column = "orderDesc"),
            Result(property = "product", column = "productId", javaType = Product::class, one = One(select = "com.github.stormwyrm.ssmadmin.dao.IProductDao.findById")))
    fun findAll(): List<Order>

    @Select("SELECT * FROM orders WHERE id = #{orderId}")
    @Results(
            Result(id = true, property = "id", column = "id"),
            Result(property = "orderNum", column = "orderNum"),
            Result(property = "orderTime", column = "orderTime"),
            Result(property = "orderStatus", column = "orderStatus"),
            Result(property = "peopleCount", column = "peopleCount"),
            Result(property = "peopleCount", column = "peopleCount"),
            Result(property = "payType", column = "payType"),
            Result(property = "orderDesc", column = "orderDesc"),
            Result(property = "product", column = "productId", javaType = Product::class, one = One(select = "com.github.stormwyrm.ssmadmin.dao.IProductDao.findById")),
            Result(property = "member",column = "memberId",javaType = Member::class,one = One(select = "com.github.stormwyrm.ssmadmin.dao.IMemberDao.findById")),
            Result(property = "travellers" ,column = "id",javaType = List::class,many = Many(select = "com.github.stormwyrm.ssmadmin.dao.ITravellerDao.findByOrderId"))
    )
    fun findById(orderId: String): Order
}
