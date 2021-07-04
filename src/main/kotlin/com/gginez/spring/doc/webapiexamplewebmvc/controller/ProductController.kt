package com.gginez.spring.doc.webapiexamplewebmvc.controller

import com.gginez.spring.doc.webapiexamplewebmvc.controller.model.CreateProductRequest
import com.gginez.spring.doc.webapiexamplewebmvc.controller.model.UpdateProductRequest
import com.gginez.spring.doc.webapiexamplewebmvc.model.Product
import org.springframework.http.ResponseEntity

interface ProductController {

    fun getAllProduct(): ResponseEntity<List<Product>>

    fun getProduct(sku: String): ResponseEntity<Product>

    fun createProduct(productToCreate: CreateProductRequest): ResponseEntity<Product>

    fun updateProduct(sku: String, productToUpdate: UpdateProductRequest): ResponseEntity<Product>

    fun delete(sku: String)

}