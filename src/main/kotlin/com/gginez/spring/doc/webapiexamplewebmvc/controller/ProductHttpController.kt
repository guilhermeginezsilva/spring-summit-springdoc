package com.gginez.spring.doc.webapiexamplewebmvc.controller

import com.gginez.spring.doc.webapiexamplewebmvc.controller.ProductHttpController.Companion.RESOURCE_PATH
import com.gginez.spring.doc.webapiexamplewebmvc.controller.model.CreateProductRequest
import com.gginez.spring.doc.webapiexamplewebmvc.controller.model.UpdateProductRequest
import com.gginez.spring.doc.webapiexamplewebmvc.exception.EntityAlreadyExistsException
import com.gginez.spring.doc.webapiexamplewebmvc.exception.ErrorResponse
import com.gginez.spring.doc.webapiexamplewebmvc.model.Product
import com.gginez.spring.doc.webapiexamplewebmvc.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import javax.validation.Valid

@ControllerAdvice(basePackageClasses = [ProductHttpController::class])
@RequestMapping(RESOURCE_PATH)
@RestController
class ProductHttpController(
        private val service: ProductService
) : DocumentedProductHttpController() {

    companion object {
        const val RESOURCE_PATH = "/products"
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(value = [EntityAlreadyExistsException::class])
    fun handleEntityAlreadyExists(ex: EntityAlreadyExistsException): ErrorResponse {
        return ErrorResponse(
                message = ex.message ?: "",
                status = HttpStatus.CONFLICT.name
        )
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = [RequestMethod.GET])
    override fun getAllProduct(): ResponseEntity<List<Product>> {
        return ResponseEntity.ok(service.findAll())
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = ["/{sku}"], method = [RequestMethod.GET])
    override fun getProduct(@PathVariable sku: String): ResponseEntity<Product> {
        val requestedProduct = service.findBySku(sku)

        return if (requestedProduct.isPresent) ResponseEntity.ok(requestedProduct.get())
        else ResponseEntity.notFound().build()
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = [RequestMethod.POST])
    override fun createProduct(@Valid @RequestBody productToCreate: CreateProductRequest): ResponseEntity<Product> {
        val createdProduct = service.create(productToCreate.toDomain())
        return ResponseEntity.created(URI.create("${RESOURCE_PATH}/${createdProduct.sku}")).body(createdProduct)
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = ["/{sku}"], method = [RequestMethod.PUT])
    override fun updateProduct(
            @PathVariable sku: String,
            @RequestBody productToUpdate: UpdateProductRequest
    ): ResponseEntity<Product> {
        val updateRequest = service.update(productToUpdate.toDomain(sku = sku))

        return if (updateRequest.isPresent) ResponseEntity.ok(updateRequest.get())
        else ResponseEntity.notFound().build()
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = ["/{sku}"], method = [RequestMethod.DELETE])
    override fun delete(
            @PathVariable sku: String
    ) {
        service.delete(sku)
    }

}




