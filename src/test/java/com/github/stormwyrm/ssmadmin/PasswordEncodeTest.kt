package com.github.stormwyrm.ssmadmin

import org.junit.Test
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class PasswordEncodeTest {

    @Test
    fun test() {
        println(BCryptPasswordEncoder().encode("123456"))
    }
}