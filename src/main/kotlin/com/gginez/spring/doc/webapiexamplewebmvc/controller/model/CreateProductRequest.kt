package com.gginez.spring.doc.webapiexamplewebmvc.controller.model

import com.gginez.spring.doc.webapiexamplewebmvc.model.Product
import javax.validation.constraints.*

data class CreateProductRequest(
        @get:Size(max = 255)
        @get:Pattern(regexp = "^.{2}-.*$", message = "sku must be well formatted")
        val sku: String,
        @get:Size(max = 255)
        @get:NotEmpty
        val name: String
) {

        fun toDomain(): Product {
                return Product(
                        sku = sku,
                        name = name
                )
        }

}
