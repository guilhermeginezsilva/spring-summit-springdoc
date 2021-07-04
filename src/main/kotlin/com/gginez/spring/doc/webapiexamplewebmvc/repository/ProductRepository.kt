package com.gginez.spring.doc.webapiexamplewebmvc.repository

import com.gginez.spring.doc.webapiexamplewebmvc.model.Product
import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RestResource

@RestResource(exported = false)
interface ProductRepository : CrudRepository<Product, String> {

}