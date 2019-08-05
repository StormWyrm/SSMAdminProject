package com.github.stormwyrm.ssmadmin.dao

import com.github.stormwyrm.ssmadmin.domain.Product
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update
import org.springframework.stereotype.Repository

@Repository("productDao")
interface IProductDao {
    val iProductDao: IProductDao
    //根据id查询产品
    @Select("SELECT * FROM product WHERE id=#{id}")
    @Throws(Exception::class)
    fun findById(id: String): Product

    //查询所有的产品信息
    @Select("SELECT * FROM product")
    @Throws(Exception::class)
    fun findAll(): List<Product>

    @Insert("INSERT INTO product(id,productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) VALUES(#{id},#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    fun save(product: Product)

    @Delete("DELETE product WHERE id = #{productId}")
    fun delete(productId : Int)

    @Update("UPDATE product SET productNum = #{productNum},productName = #{productName},cityName = #{cityName},departureTime = #{departureTime},productPrice = #{productPrice},productDesc = #{productDesc},productStatus = {productStatus} WHERE id = #{id};")
    fun update(product: Product)
}
