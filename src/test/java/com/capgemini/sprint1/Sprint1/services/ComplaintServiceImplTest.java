package com.capgemini.sprint1.Sprint1.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


import java.util.List;


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
import com.capgemini.sprint1.Sprint1.exceptions.ComplaintNotFoundException;
import com.capgemini.sprint1.Sprint1.repositories.ClientRepository;
import com.capgemini.sprint1.Sprint1.repositories.ComplaintRepository;
import com.capgemini.sprint1.Sprint1.repositories.EngineerRepository;
import com.capgemini.sprint1.Sprint1.repositories.ProductRepository;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ComplaintServiceImplTest {
	
	@InjectMocks
	private ComplaintServiceImpl serviceImpl;
	
	@Mock
	private ComplaintRepository complaintRepository;
	
	@Mock
	private ClientRepository clientRepository;
	
	@Mock 
	private EngineerRepository engineerRepository;
	
	@Mock
	private ProductRepository productRepository;
	
	
	Engineer engineer;
	Product product;
	Client client;
	Complaint complaint;
	
	@BeforeEach
	void setUp() throws Exception {
		product = new Product(23L, "prod1", "cc", null, 1, null, true, List.of());
		complaint = new Complaint(1L, "complaint1", "open", null, engineer, null);
		engineer = new Engineer(1L, "passsword", "eng1", false, List.of(complaint));
		client = new Client(1L,"cli1","adr1",12345,null,List.of(complaint));
		
	}

	@Test 
	@DisplayName("Complaint not found Exception test")
	void testGetEngineer() throws ComplaintNotFoundException {
		when(complaintRepository.save(complaint)).thenReturn(complaint);
		when(engineerRepository.getEngineerByComplaintId(complaint.getComplaintId())).thenReturn(engineer);
		
		assertThrows(ComplaintNotFoundException.class, ()->{
			serviceImpl.getEngineer(complaint.getComplaintId());
		});
	}
	
	@Test
	@DisplayName("Get Complaints List - Client not found")
	void testGetComplaints() throws ClientNotFoundException {
		List<Complaint> complaints = List.of(complaint);
		when(clientRepository.save(client)).thenReturn(client);
		
		
		when(complaintRepository.findAll()).thenReturn(complaints);
		
		assertThrows(ClientNotFoundException.class, ()-> {
			serviceImpl.getComplaints(client.getClientId());
		});
	}
	
	@Test 
	@DisplayName("Product not found Exception test")
	void testGetProduct() {

//		when(productRepository.save(product)).thenReturn(product);
//		when(engineerRepository.getEngineerByComplaintId(complaint.getComplaintId())).thenReturn(engineer);
//		when(complaintRepository.existsById(complaint.getComplaintId())).thenReturn(true);
//		when(productRepository.getProductByComplaintId(complaint.getComplaintId())).thenReturn(null);
		
//		assertThrows(ProductNotFoundException.class, ()->{
//			serviceImpl.getProduct(complaint.getComplaintId());
//		});
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test
	@DisplayName("Get Resolved Complaints")
	void testGetResolvedComplaints() {
		when(complaintRepository.save(complaint)).thenReturn(complaint);
		when(engineerRepository.getEngineerByComplaintId(complaint.getComplaintId())).thenReturn(engineer);
		
		assertThrows(ComplaintNotFoundException.class, ()->{
			serviceImpl.getEngineer(complaint.getComplaintId());
		});
	}

}
