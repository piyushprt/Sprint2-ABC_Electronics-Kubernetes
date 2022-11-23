package com.capgemini.sprint1.Sprint1.services;


import org.springframework.stereotype.Service;

import com.capgemini.sprint1.Sprint1.entities.Client;
import com.capgemini.sprint1.Sprint1.entities.Engineer;
import com.capgemini.sprint1.Sprint1.exceptions.ClientNotFoundException;
import com.capgemini.sprint1.Sprint1.exceptions.EngineerNotFoundException;

@Service
public interface ClientService {
	
	public void addClient(Client client);
	
	public void buyProduct(long clientId, long  productId) throws Exception;
	
	public Client getClientById(long clientId) throws ClientNotFoundException;
	
	public Engineer getEngineerByComplaintId(long clientId ,long complaintId) throws ClientNotFoundException, EngineerNotFoundException;

	public boolean login(long userId, String password) throws Exception;

}
