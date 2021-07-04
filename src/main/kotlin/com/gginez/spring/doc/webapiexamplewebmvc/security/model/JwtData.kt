package com.gginez.spring.doc.webapiexamplewebmvc.security.model

data class JwtData(
        val id: Long,
        val type: String = "Basic",
        val jwt: String,
        val username: String,
        val roles: List<String>
) {

}