package com.gginez.spring.doc.webapiexamplewebmvc.security.controller

import com.gginez.spring.doc.webapiexamplewebmvc.security.model.JwtData
import com.gginez.spring.doc.webapiexamplewebmvc.security.model.LoginRequest
import com.gginez.spring.doc.webapiexamplewebmvc.security.model.SignupRequest
import com.gginez.spring.doc.webapiexamplewebmvc.security.model.User
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.http.ResponseEntity


interface AuthController {

    @Operation(summary = "Authenticate user",
            description = "This endpoint authenticates the user",
            responses = [
                ApiResponse(
                        responseCode = "200",
                        description = "OK",
                        content = [Content(
                                mediaType = "application/json",
                                schema = Schema(implementation = JwtData::class)
                        )
                        ]
                )
            ])
    fun authenticateUser(loginRequest: LoginRequest): ResponseEntity<JwtData>

    @Operation(summary = "Register new user",
            description = "This endpoint registers a new user",
            responses = [
                ApiResponse(
                        responseCode = "201",
                        description = "CREATED",
                        content = [Content(
                                mediaType = "application/json",
                                schema = Schema(implementation = User::class)
                        )
                        ]
                )
            ])
    fun registerUser(signupRequest: SignupRequest): ResponseEntity<User>

}