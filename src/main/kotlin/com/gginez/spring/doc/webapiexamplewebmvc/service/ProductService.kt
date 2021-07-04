package com.gginez.spring.doc.webapiexamplewebmvc.service

import com.gginez.spring.doc.webapiexamplewebmvc.exception.EntityAlreadyExistsException
import com.gginez.spring.doc.webapiexamplewebmvc.model.Product
import com.gginez.spring.doc.webapiexamplewebmvc.repository.ProductRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductService(
        private val repository: ProductRepository
) {
    fun findBySku(sku: String): Optional<Product> {
        return repository.findById(sku)
    }

    fun create(productToCreate: Product): Product {
        if(repository.existsById(productToCreate.sku)) {
            throw EntityAlreadyExistsException("User already exists")
        }

        return repository.save(productToCreate)
    }

    fun update(productToUpdate: Product): Optional<Product> {
        requireNotNull(productToUpdate.sku)

        return repository.findById(productToUpdate.sku)
                .map { repository.save(productToUpdate) }
    }

    fun findAll(): List<Product> {
        return repository.findAll().toList()
    }

    fun delete(sku: String) {
        repository.findById(sku)
                .ifPresent { repository.delete(it) }
    }
}