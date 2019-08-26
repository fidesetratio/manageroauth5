package com.app.rest;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.controller.datatables.SimplePaginator;
import com.app.controller.datatables.TablePaginator;
import com.app.controller.datatables.models.PaginationCriteria;
import com.app.controller.datatables.models.TablePage;
import com.app.model.OauthUsers;
import com.app.repo.UserDetailRepo;
import com.app.service.UserServiceTable;

@RestController
@RequestMapping("/manager")
public class UserDetailRest {
	
	@Autowired
	private UserDetailRepo userDetailRepo;
	
	private static Logger logger  = Logger.getLogger(UserDetailRest.class);
	
	
	@RequestMapping(value="/listusers",method=RequestMethod.GET)
	public ResponseEntity<List<OauthUsers>> listUsers(){
		List<OauthUsers> list = new ArrayList<OauthUsers>();
		list = (List<OauthUsers>) userDetailRepo.findAll();
		return new ResponseEntity<List<OauthUsers>>(list,HttpStatus.OK);

	}
	
	@RequestMapping(value="/listuserstwo",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody TablePage listUsersTwo(@RequestBody PaginationCriteria treq){
		logger.info("for bey");
		TablePaginator paginator = new SimplePaginator(new UserServiceTable(userDetailRepo));
		TablePage tablePage =  paginator.getPage(treq);
		return tablePage;
		
	}
	
	
	
	
}
