package com.capgemini.sprint1.Sprint1.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

//import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.sprint1.Sprint1.repositories.ClientRepository;
import com.capgemini.sprint1.Sprint1.repositories.EngineerRepository;
import com.capgemini.sprint1.Sprint1.repositories.ProductRepository;
import com.capgemini.sprint1.Sprint1.entities.Client;
import com.capgemini.sprint1.Sprint1.entities.Engineer;
import com.capgemini.sprint1.Sprint1.entities.Product;
import com.capgemini.sprint1.Sprint1.exceptions.ClientNotFoundException;
import com.capgemini.sprint1.Sprint1.exceptions.EngineerNotFoundException;
import com.capgemini.sprint1.Sprint1.exceptions.InvalidLoginCredentials;
import com.capgemini.sprint1.Sprint1.exceptions.ProductAlreadyOwnedException;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private EngineerRepository engineerRepository;

	@Override
	public void addClient(Client client) {
		clientRepository.save(client);
	}
	
	@Override
	public boolean login(long userId, String password) throws Exception{
		Optional<Client> client = clientRepository.findById(userId);
		
		if(client.isPresent()) {
			String pass = client.get().getPassword();
			
			if(pass.equals(password)) {
				return true;
			} else {
				throw new InvalidLoginCredentials();
			}
		} else {
			throw new ClientNotFoundException();
		}
	}

	@Override
	@Transactional
	public void buyProduct(long clientId, long productId) throws Exception {
		Optional<Product> getProduct = productRepository.findById(productId);
		
		if(clientRepository.existsById(clientId)) {
			if(getProduct.isPresent()) {
				Product product = getProduct.get();
				if(product.isOwned()) {
					throw new ProductAlreadyOwnedException();
				} else {
					product.setDateOfPurchase(LocalDate.now());
					product.setWarrentyDate(product.getDateOfPurchase().plusYears(product.getWarrentyYears()));
					product.setOwned(true);
					
					Client client = clientRepository.findById(clientId).get();
					List<Product> getProducts = client.getProductList();
					getProducts.add(product);
					client.setProductList(getProducts);
					clientRepository.save(client);
				}
			}
		} else {
			throw new ClientNotFoundException();
		}
	}

	@Override
	public Client getClientById(long clientId) throws ClientNotFoundException {
//		Optional<Client> getClient = clientRepository.findById(clientId);
		
		if(!clientRepository.existsById(clientId)) {
			throw new ClientNotFoundException();
		}
		
		return clientRepository.findById(clientId).get();
	}

	@Override
	public Engineer getEngineerByComplaintId(long clientId, long complaintId) throws ClientNotFoundException, EngineerNotFoundException {
		Optional<Client> getClient = clientRepository.findById(clientId);
		
		if(!getClient.isPresent()) {
			throw new ClientNotFoundException();
		}
		
		Engineer engineer = engineerRepository.getEngineerByComplaintId(complaintId);
		if(engineer==null) {
			throw new EngineerNotFoundException();
		}
		
		
		return engineer;
	}

	
	
	
	
	

}
