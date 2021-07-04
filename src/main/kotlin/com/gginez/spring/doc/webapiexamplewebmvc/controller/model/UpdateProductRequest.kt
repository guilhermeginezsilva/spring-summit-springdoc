package com.gginez.spring.doc.webapiexamplewebmvc.controller.model

import com.gginez.spring.doc.webapiexamplewebmvc.model.Product
import javax.validation.constraints.*

data class UpdateProductRequest(
        @get:Size(max = 255)
        @get:NotEmpty
        val name: String
) {

        fun toDomain(sku: String): Product {
                return Product(
                        sku = sku,
                        name = name
                )
        }

}
