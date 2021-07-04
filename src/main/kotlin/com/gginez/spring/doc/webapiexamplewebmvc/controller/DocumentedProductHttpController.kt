package com.gginez.spring.doc.webapiexamplewebmvc.controller

import com.gginez.spring.doc.webapiexamplewebmvc.controller.model.CreateProductRequest
import com.gginez.spring.doc.webapiexamplewebmvc.controller.model.UpdateProductRequest
import com.gginez.spring.doc.webapiexamplewebmvc.model.Product
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.http.ResponseEntity


//@ApiResponse(responseCode = "401", description = "Authentication Failure")

@SecurityRequirement(name = "bearer-key")
abstract class DocumentedProductHttpController : ProductController {

    @Operation(summary = "Get all products",
            description = "This endpoint can only be called by a logger user",
            responses = [
                ApiResponse(
                        responseCode = "200",
                        description = "OK",
                        content = [Content(
                                mediaType = "application/json",
                                array = ArraySchema(schema = Schema(implementation = Product::class))

                        )
                        ]
                )
            ])
    abstract override fun getAllProduct(): ResponseEntity<List<Product>>

    @Operation(summary = "Get product",
            description = "This endpoint can only be called by a logger user",
            responses = [
                ApiResponse(
                        responseCode = "200",
                        description = "OK",
                        content = [Content(
                                mediaType = "application/json",
                                schema = Schema(implementation = Product::class)
                        )
                        ]
                )
            ])
    abstract override fun getProduct(sku: String): ResponseEntity<Product>

    @Operation(summary = "Creates a product",
            description = "This endpoint can only be called by a logger user",
            responses = [
                ApiResponse(
                        responseCode = "201",
                        description = "Created",
                        content = [Content(
                                mediaType = "application/json",
                                schema = Schema(implementation = Product::class)
                        )
                        ]
                )
            ])
    abstract override fun createProduct(productToCreate: CreateProductRequest): ResponseEntity<Product>

    @Operation(summary = "Updates a product",
            description = "This endpoint can only be called by a logger user",
            responses = [
                ApiResponse(
                        responseCode = "200",
                        description = "OK",
                        content = [Content(
                                mediaType = "application/json",
                                schema = Schema(implementation = Product::class)
                        )
                        ]
                )
            ])
    abstract override fun updateProduct(sku: String, productToUpdate: UpdateProductRequest): ResponseEntity<Product>

    @Operation(summary = "Deletes a product",
            description = "This endpoint can only be called by a logger user",
            responses = [
                ApiResponse(
                        responseCode = "204",
                        description = "No Content"
                )
            ])
    abstract override fun delete(sku: String)

}