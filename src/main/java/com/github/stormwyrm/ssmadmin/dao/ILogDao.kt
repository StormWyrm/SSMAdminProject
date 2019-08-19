package com.github.stormwyrm.ssmadmin.dao

import com.github.stormwyrm.ssmadmin.domain.Log
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Select
import org.springframework.stereotype.Repository

@Repository("logDao")
interface ILogDao{
    @Insert("INSERT INTO log VALUES (#{id},#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    fun save(log : Log)

    @Select("SELECT * FROM log")
    fun findAll() : List<Log>

    @Select("SELECT COUNT(*) FROM log")
    fun getCount() : Long
}
