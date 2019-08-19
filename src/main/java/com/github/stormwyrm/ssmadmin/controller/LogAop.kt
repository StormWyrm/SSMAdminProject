package com.github.stormwyrm.ssmadmin.controller

import com.github.stormwyrm.ssmadmin.domain.Log
import com.github.stormwyrm.ssmadmin.service.ILogService
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestMapping
import java.lang.reflect.Method
import java.util.*
import javax.servlet.http.HttpServletRequest

@Component
@Aspect
class LogAop {
    @Autowired
    private lateinit var request: HttpServletRequest

    @Autowired
    private lateinit var logService: ILogService

    private lateinit var visitTime: Date//访问日期
    private lateinit var clazz: Class<*>//访问的类
    private lateinit var method: Method//访问方法

    @Before("execution(* com.github.stormwyrm.ssmadmin.controller.*.*(..))")
    fun doBefore(jp: JoinPoint) {
        visitTime = Date()
        clazz = jp.target.javaClass
        val methodName = jp.signature.name
        val args = jp.args
        //获取具体执行的方法的Method对象
        if (args.isNullOrEmpty()) {
            method = clazz.getMethod(methodName) //只能获取无参数的方法
        } else {
            val classArgs = arrayOfNulls<Class<*>>(args.size)
            for (i in args.indices) {
                classArgs[i] = args[i].javaClass
            }
            method = clazz.getMethod(methodName, *classArgs)
        }
    }

    @After("execution(* com.github.stormwyrm.ssmadmin.controller.*.*(..))")
    fun doAfter(jp: JoinPoint) {
        val time = Date().time - visitTime.time //获取访问的时长
        var url = ""
        if (clazz != null && method != null && clazz != LogAop::class.java) {
            val classAnnotation = clazz.getAnnotation(RequestMapping::class.java)
            classAnnotation?.let {
                val classValues = it.value
                val methodAnnotation = method.getAnnotation(RequestMapping::class.java)
                url = methodAnnotation?.let {
                    val methodValues = it.value
                    classValues[0] + methodValues[0]
                } ?: classValues[0]

                //获取访问ip
                val ip = request.remoteAddr

                //获取当前操作的用户
                val context = SecurityContextHolder.getContext()//从上下文中获了当前登录的用户
                val user = context.authentication.principal as User
                val username = user.username

                //将日志相关信息封装到SysLog对象
                val log = Log()
                log.id = "LOG ${logService.getCount()}"
                log.executionTime = time
                log.ip = ip
                log.method = "[类名] ${clazz.name} [方法名] ${method.name}"
                log.url = url
                log.username = username
                log.visitTime = visitTime

                logService.save(log)
            }
        }

    }
}