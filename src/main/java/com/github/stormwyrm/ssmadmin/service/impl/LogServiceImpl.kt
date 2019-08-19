package com.github.stormwyrm.ssmadmin.service.impl

import com.github.stormwyrm.ssmadmin.dao.ILogDao
import com.github.stormwyrm.ssmadmin.domain.Log
import com.github.stormwyrm.ssmadmin.service.ILogService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service("logService")
class LogServiceImpl : ILogService {
    @Autowired
    private lateinit var logDao: ILogDao

    override fun save(log: Log) {
        logDao.save(log)
    }

    override fun findAll(): List<Log> {
        return logDao.findAll()
    }

    override fun getCount(): Long {
        return logDao.getCount()
    }
}