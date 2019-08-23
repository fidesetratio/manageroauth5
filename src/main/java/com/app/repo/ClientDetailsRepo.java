package com.app.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.app.model.ClientDetails;

public interface ClientDetailsRepo extends PagingAndSortingRepository<ClientDetails,String>,JpaSpecificationExecutor{
	public ClientDetails findByClientId(String clientId);
	@Query("SELECT c FROM ClientDetails c WHERE c.clientId LIKE %:search%")
	Page<ClientDetails> searchBy(@Param("search") String search,Pageable page);
	

}
