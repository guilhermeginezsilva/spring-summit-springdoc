package com.gginez.spring.doc.webapiexamplewebmvc.model

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "products")
data class Product(
        @Id
        val sku: String,
        @Column(nullable = false)
        val name: String,
        @Column(nullable = false)
        val createdAt: LocalDateTime = LocalDateTime.now()
) {


}