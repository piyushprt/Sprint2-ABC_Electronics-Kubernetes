package com.capgemini.sprint1.Sprint1.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.capgemini.sprint1.Sprint1.entities.Client;
import com.capgemini.sprint1.Sprint1.entities.Complaint;
import com.capgemini.sprint1.Sprint1.entities.Engineer;
import com.capgemini.sprint1.Sprint1.entities.Product;
import com.capgemini.sprint1.Sprint1.exceptions.ClientNotFoundException;
import com.capgemini.sprint1.Sprint1.exceptions.EngineerNotFoundException;
import com.capgemini.sprint1.Sprint1.exceptions.ProductAlreadyOwnedException;
import com.capgemini.sprint1.Sprint1.repositories.ClientRepository;
import com.capgemini.sprint1.Sprint1.repositories.EngineerRepository;
import com.capgemini.sprint1.Sprint1.repositories.ProductRepository;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ClientServiceImplTest {
	
	@InjectMocks
	private ClientServiceImpl clientServiceImpl;
	
	@Mock
	private ClientRepository clientRepository;
	
	@Mock
	private ProductRepository productRepository;
	
	@Mock
	private EngineerRepository engineerRepository;
	
	Client client;
	Client client2;
	Client client3;
	Product product;
	Product product2;
	Engineer engineer;
	Engineer engineer2;
	Complaint complaint;
	
	@BeforeEach
	void setUp() throws Exception {
		//produtList and complaintList
		client = new Client(1L,"cli1","adr1",12345,null,null);
		client2 = new Client(2L,"cli2","adr2",23456,null,null);
		client3 = new Client(3L,"cli3","adr3",34567,null,null);
		product = new Product(1, "AC", "Home", LocalDate.now(), 1, null, false, null);
		product2 = new Product(2, "TV", "Home", null, 2, null, true, null);
		engineer = null;
		complaint = new Complaint(1L, "complaint1", "open", null, engineer, null);
		engineer2 = engineer = new Engineer(1L ,"stud", "eng1", true, List.of(complaint));
	}

	@Test
	@DisplayName("Add client")
	void testAddClient() throws ClientNotFoundException {
		when(clientRepository.save(client)).thenReturn(client);
		
		when(clientRepository.existsById(client.getClientId())).thenReturn(true);
		
		when(clientRepository.findById(client.getClientId())).thenReturn(Optional.of(client));
		
		assertEquals(client, clientServiceImpl.getClientById(client.getClientId()));
	}
	
	
	@Test
	@DisplayName("Try to get client which do not exist")
	void testGetClientById() {		
		when(clientRepository.existsById(9L)).thenReturn(false);
		
		assertThrows(ClientNotFoundException.class, ()-> {
			clientServiceImpl.getClientById(9L);
		});
	}
	
	@Test
	@DisplayName("Buy Product-Throw client not found")
	void testBuyProduct1() {
		when(productRepository.findById(product.getModelNumber())).thenReturn(Optional.of(product));
		when(clientRepository.existsById(client.getClientId())).thenReturn(false);
		
		assertThrows(ClientNotFoundException.class, ()-> {
			clientServiceImpl.buyProduct(client.getClientId(), product.getModelNumber());
		});
	}
	
	@Test
	@DisplayName("Buy Product-Throw already owned")
	void testBuyProduct2() {
		when(productRepository.findById(product2.getModelNumber())).thenReturn(Optional.of(product2));
		when(clientRepository.existsById(client.getClientId())).thenReturn(true);
		
		assertThrows(ProductAlreadyOwnedException.class, ()-> {
			clientServiceImpl.buyProduct(client.getClientId(), product2.getModelNumber());
		});
	}
	
	@Test
	@DisplayName("Get Engineer-Throw engineer not exist")
	void testGetEngineerByComplaintId1() {
		when(clientRepository.findById(client.getClientId())).thenReturn(Optional.of(client));
		assertThrows(EngineerNotFoundException.class, ()-> {
			clientServiceImpl.getEngineerByComplaintId(client.getClientId(), complaint.getComplaintId());
		});
	}
	
	@Test
	@DisplayName("Get Engineer-Throw client not exist")
	void testGetEngineerByComplaintId2() {
		when(engineerRepository.getEngineerByComplaintId(complaint.getComplaintId())).thenReturn(engineer2);
		assertThrows(ClientNotFoundException.class, ()-> {
			clientServiceImpl.getEngineerByComplaintId(client.getClientId(), complaint.getComplaintId());
		});
	}

}
