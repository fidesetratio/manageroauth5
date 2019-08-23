package com.app.service;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.app.controller.datatables.data.DataServiceBase;
import com.app.controller.datatables.data.TableDataException;
import com.app.controller.datatables.models.PaginationCriteria;
import com.app.model.OauthUsers;
import com.app.repo.UserDetailRepo;

public class UserServiceTable extends DataServiceBase<OauthUsers>{
	
	private UserDetailRepo userDetailDao;
	private Page<OauthUsers> page;
	private static Logger logger = Logger.getLogger(UserServiceTable.class);
	
	
	public UserServiceTable(UserDetailRepo userDetailRepo) {
		this.userDetailDao = userDetailRepo;
		Pageable pageable = PageRequest.of(0, 10);
		page = this.userDetailDao.findAll(pageable);
	}

	@Override
	public long countTotalEntries() throws TableDataException {
		// TODO Auto-generated method stub
		return page.getTotalElements();
	}

	@Override
	public long countFilteredEntries(PaginationCriteria paginationCriteria) throws TableDataException {
		// TODO Auto-generated method stub
		return page.getTotalElements();
	}

	@Override
	protected List<OauthUsers> getData(PaginationCriteria paginationCriteria) throws TableDataException {
		
		Pageable pageable = PageRequest.of(0, 10);
		
		List<OauthUsers> list = new ArrayList<OauthUsers>();
		page = this.userDetailDao.findAll(pageable);
		return page.getContent();
	}

}
