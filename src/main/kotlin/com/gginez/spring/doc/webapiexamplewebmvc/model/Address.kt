package com.gginez.spring.doc.webapiexamplewebmvc.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "addresses")
data class Address (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val name: String,
        val street: String,
        @Column(name="num")
        val number: Int,
        val city: String,
        val country: String,
        val createdAt: LocalDateTime? = LocalDateTime.now()
) {

}