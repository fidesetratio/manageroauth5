package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.ClientDetails;
import com.app.repo.ClientDetailsRepo;

@Service
public class ClientsService {
	
	@Autowired
	private ClientDetailsRepo clientRepo;
	
	

	
	public List<ClientDetails> findAll(){
		return (List<ClientDetails>) clientRepo.findAll();
	}
	
	public void add(ClientDetails clientDetail) {
		this.clientRepo.save(clientDetail);
	}
	
	public ClientDetails findByClientId(String clientId) {
		return this.clientRepo.findByClientId(clientId);
	}
	public void clientDeleteByClientId(ClientDetails clientDetails) {
		this.clientRepo.delete(clientDetails);
	}
}
