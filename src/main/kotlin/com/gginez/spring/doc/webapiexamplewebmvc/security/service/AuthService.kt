package com.gginez.spring.doc.webapiexamplewebmvc.security.service

import com.gginez.spring.doc.webapiexamplewebmvc.exception.UserSignupException
import com.gginez.spring.doc.webapiexamplewebmvc.security.jwt.JwtUtils
import com.gginez.spring.doc.webapiexamplewebmvc.security.model.JwtData
import com.gginez.spring.doc.webapiexamplewebmvc.security.model.LoginRequest
import com.gginez.spring.doc.webapiexamplewebmvc.security.model.SignupRequest
import com.gginez.spring.doc.webapiexamplewebmvc.security.model.User
import com.gginez.spring.doc.webapiexamplewebmvc.security.repository.UserRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService (
        private val userRepository: UserRepository,
        private val authenticationManager: AuthenticationManager,
        private val passwordEncoder: PasswordEncoder,
        private val jwtUtils: JwtUtils
) {

    fun authenticate(loginRequest: LoginRequest): JwtData {
        val authentication: Authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password)
        )

        SecurityContextHolder.getContext().authentication = authentication

        val jwt: String = jwtUtils.generateJwtToken(authentication)

        val user: User = authentication.principal as User

        return JwtData(
                id = user.id!!,
                jwt = jwt,
                username = user.user,
                roles = user.authorities.map { it.authority }
        )
    }

    fun register(signupRequest: SignupRequest): User {
        if (userRepository.existsByUser(signupRequest.username)) {
            throw UserSignupException("Error: Username is already taken!")
        }

        val newUser = User(
                user = signupRequest.username,
                pass = passwordEncoder.encode(signupRequest.password)
        )
        return userRepository.save(newUser)
    }

}