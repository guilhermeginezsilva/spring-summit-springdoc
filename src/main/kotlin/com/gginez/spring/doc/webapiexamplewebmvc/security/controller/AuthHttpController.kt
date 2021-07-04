package com.gginez.spring.doc.webapiexamplewebmvc.security.controller

import com.gginez.spring.doc.webapiexamplewebmvc.exception.EntityAlreadyExistsException
import com.gginez.spring.doc.webapiexamplewebmvc.exception.ErrorResponse
import com.gginez.spring.doc.webapiexamplewebmvc.security.model.JwtData
import com.gginez.spring.doc.webapiexamplewebmvc.security.model.LoginRequest
import com.gginez.spring.doc.webapiexamplewebmvc.security.model.SignupRequest
import com.gginez.spring.doc.webapiexamplewebmvc.security.model.User
import com.gginez.spring.doc.webapiexamplewebmvc.security.service.AuthService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import javax.validation.Valid

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
class AuthHttpController(
        private val service: AuthService
) : AuthController {

    @PostMapping("/signin")
    override fun authenticateUser(@Valid  @RequestBody loginRequest: LoginRequest): ResponseEntity<JwtData> {
        val jwtData = service.authenticate(loginRequest)
        return ResponseEntity.ok(jwtData)
    }

    @PostMapping("/signup")
    override fun registerUser(@Valid @RequestBody signupRequest: SignupRequest): ResponseEntity<User> {
        val registeredUser = service.register(signupRequest)
        return ResponseEntity.created(URI.create("/users/${registeredUser.id}")).body(registeredUser)
    }

}