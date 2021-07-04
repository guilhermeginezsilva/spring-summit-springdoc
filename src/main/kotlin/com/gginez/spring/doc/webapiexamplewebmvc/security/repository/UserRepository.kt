package com.gginez.spring.doc.webapiexamplewebmvc.security.repository

import com.gginez.spring.doc.webapiexamplewebmvc.security.model.User
import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RestResource
import java.util.*

@RestResource(exported = false)
interface UserRepository : CrudRepository<User, Long> {

    fun findByUser(user: String): Optional<User>

    fun existsByUser(user: String): Boolean

}