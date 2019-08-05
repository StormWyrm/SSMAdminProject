package com.github.stormwyrm.ssmadmin.dao

import com.github.stormwyrm.ssmadmin.domain.Traveller
import org.apache.ibatis.annotations.Select

interface ITravellerDao {

    @Select("SELECT * FROM traveller WHERE id IN (SELECT travellerId FROM orders_traveller WHERE orderId = #{orderId})")
    fun findByOrderId(orderId: String): List<Traveller>
}