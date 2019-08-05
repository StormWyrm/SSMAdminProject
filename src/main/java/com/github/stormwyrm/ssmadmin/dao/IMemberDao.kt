package com.github.stormwyrm.ssmadmin.dao

import com.github.stormwyrm.ssmadmin.domain.Member
import org.apache.ibatis.annotations.Select

interface IMemberDao{

    @Select("SELECT * FROM member WHERE id = #{memberId}")
    fun findById(memberId : String) : Member
}
