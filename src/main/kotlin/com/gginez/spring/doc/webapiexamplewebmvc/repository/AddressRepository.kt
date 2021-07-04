package com.gginez.spring.doc.webapiexamplewebmvc.repository

import com.gginez.spring.doc.webapiexamplewebmvc.model.Address
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@SecurityRequirement(name = "bearer-key")
@RepositoryRestResource(collectionResourceRel = "addresses", path = "addresses")
interface AddressRepository : PagingAndSortingRepository<Address, Long> {

}