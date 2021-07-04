package com.gginez.spring.doc.webapiexamplewebmvc.security.model

import net.minidev.json.annotate.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size


@Entity
@Table(name = "users",
        uniqueConstraints = [
            UniqueConstraint(columnNames = ["username"])
        ])
data class User (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        @NotBlank
        @Size(max = 40)
        @Column( name="username")
        val user: String,
        @JsonIgnore
        @NotBlank
        @Size(max = 120)
        @Column( name="password")
        val pass: String
) : UserDetails {

    companion object {
        const val MAIN_ROLE = "main";
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority(MAIN_ROLE))
    }

    override fun getPassword(): String {
        return pass
    }

    override fun getUsername(): String {
        return user
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }


}