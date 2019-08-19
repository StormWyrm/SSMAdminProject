package com.github.stormwyrm.ssmadmin.service

import com.github.stormwyrm.ssmadmin.domain.Log

interface ILogService {
    fun save(log: Log)

    fun findAll(): List<Log>

    fun getCount() : Long
}