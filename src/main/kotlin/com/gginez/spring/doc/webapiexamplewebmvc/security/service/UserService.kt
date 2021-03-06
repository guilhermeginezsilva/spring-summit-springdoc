package com.gginez.spring.doc.webapiexamplewebmvc.security.service

import com.gginez.spring.doc.webapiexamplewebmvc.security.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
        private val userRepository: UserRepository
) : UserDetailsService {

    @Transactional
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        return userRepository.findByUser(username)
                .orElseThrow { UsernameNotFoundException("User Not Found with username: $username") }
    }

}