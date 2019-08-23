package com.app.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.app.model.OauthUsers;

public interface UserDetailRepo extends PagingAndSortingRepository<OauthUsers,Long> {
	
}
