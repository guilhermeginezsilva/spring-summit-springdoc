package com.gginez.spring.doc.webapiexamplewebmvc.security.jwt

import com.gginez.spring.doc.webapiexamplewebmvc.config.Loggable
import com.gginez.spring.doc.webapiexamplewebmvc.security.model.User
import io.jsonwebtoken.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtils : Loggable {

    @Value("\${app.jwtSecret}")
    lateinit var jwtSecret: String

    @Value("\${app.jwtExpirationMs}")
    lateinit var jwtExpirationMs: String

    fun generateJwtToken(authentication: Authentication): String {
        val userPrincipal: User = authentication.getPrincipal() as User
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(Date())
                .setExpiration(Date(Date().time + jwtExpirationMs.toLong()))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact()
    }

    fun getUserNameFromJwtToken(token: String): String {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).body.subject
    }

    fun validateJwtToken(authToken: String): Boolean {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken)
            return true
        } catch (e: SignatureException) {
            log.error("Invalid JWT signature: {}", e.message)
        } catch (e: MalformedJwtException) {
            log.error("Invalid JWT token: {}", e.message)
        } catch (e: ExpiredJwtException) {
            log.error("JWT token is expired: {}", e.message)
        } catch (e: UnsupportedJwtException) {
            log.error("JWT token is unsupported: {}", e.message)
        } catch (e: IllegalArgumentException) {
            log.error("JWT claims string is empty: {}", e.message)
        }
        return false
    }

}