package com.app.service;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.app.controller.datatables.data.DataServiceBase;
import com.app.controller.datatables.data.TableDataException;
import com.app.controller.datatables.models.OrderingCriteria;
import com.app.controller.datatables.models.PaginationCriteria;
import com.app.controller.datatables.models.SearchCriteria;
import com.app.model.ClientDetails;
import com.app.repo.ClientDetailsRepo;
import com.app.repo.ClientSpecification;
import com.app.utils.SearchOperation;
import com.app.utils.SpecSearchCriteria;

public class ClientServiceTable extends DataServiceBase<ClientDetails>{

	private ClientDetailsRepo clientDetailRepo;
	private Page<ClientDetails> page;
	private static Logger logger = Logger.getLogger(ClientServiceTable.class);
	
	
	public ClientServiceTable(ClientDetailsRepo clientDetailRepo) {
		this.clientDetailRepo = clientDetailRepo;
		Pageable pageable = PageRequest.of(0, 10);
		page = this.clientDetailRepo.findAll(pageable);
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
	protected List<ClientDetails> getData(PaginationCriteria paginationCriteria) throws TableDataException {

		
		
		
		List<OrderingCriteria> order = paginationCriteria.getOrder();
		SearchCriteria search = paginationCriteria.getSearch();
		int draw = paginationCriteria.getDraw();
		int length = paginationCriteria.getLength();
		int start = paginationCriteria.getStart();
		
		System.out.println("draw:"+draw);
		System.out.println("length:"+length);

		System.out.println("start:"+start);
		String searchvalue = null;
		int p = Math.round(start/length);
		System.out.println("p:"+p);
	
		Pageable pageable = PageRequest.of(p, length);
		
		if(order.size()>0) {
			for(OrderingCriteria criteria : order) {
				int column = criteria.getColumn();
				String dir = criteria.getDir();
				System.out.println("column:"+column);
				System.out.println("direction:"+dir);
				
				
				if(column == 0) {
					if(dir.equals("asc")) {
						pageable =  PageRequest.of(p, length, Sort.by("clientId").ascending());

					}else {
						pageable =  PageRequest.of(p, length, Sort.by("clientId").descending());
					}
				};
				
				
				if(column == 1) {
					if(dir.equals("asc")) {
						pageable =  PageRequest.of(p, length, Sort.by("clientId").ascending());

					}else {
						pageable =  PageRequest.of(p, length, Sort.by("clientId").descending());
					}
				};
				
				if(column == 2) {
					if(dir.equals("asc")) {
						pageable =  PageRequest.of(p, length, Sort.by("resourceIds").ascending());

					}else {
						pageable =  PageRequest.of(p, length, Sort.by("resourceIds").descending());
					}
				};
				
				if(column == 3) {
					if(dir.equals("asc")) {
						pageable =  PageRequest.of(p, length, Sort.by("clientSecret").ascending());

					}else {
						pageable =  PageRequest.of(p, length, Sort.by("clientSecret").descending());
					}
				};
				if(column == 4) {
					if(dir.equals("asc")) {
						pageable =  PageRequest.of(p, length, Sort.by("accessTokenValidity").ascending());

					}else {
						pageable =  PageRequest.of(p, length, Sort.by("accessTokenValidity").descending());
					}
				};
			
			}
		}
		
		page = this.clientDetailRepo.findAll(pageable);
		 
		 
		if(search != null) {
			searchvalue = search.getValue();
			if(searchvalue !=  null && !searchvalue.trim().equals("")) {
				System.out.println("search value="+searchvalue);
				ClientSpecification clientIdSpecification = new ClientSpecification(new SpecSearchCriteria("clientId", SearchOperation.CONTAINS, searchvalue));
				ClientSpecification resourceIdSpecification = new ClientSpecification(new SpecSearchCriteria("resourceIds", SearchOperation.CONTAINS, searchvalue));
				ClientSpecification clientSecretSpecification = new ClientSpecification(new SpecSearchCriteria("clientSecret", SearchOperation.CONTAINS, searchvalue));
				page = this.clientDetailRepo.findAll(Specification.where(clientIdSpecification).or(resourceIdSpecification).or(clientSecretSpecification),pageable);
			}
			
		}
	
		List<ClientDetails> list = new ArrayList<ClientDetails>();
		
		return page.getContent();
	}

}
