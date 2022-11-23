package com.capgemini.sprint1.Sprint1.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.capgemini.sprint1.Sprint1.entities.Admin;
import com.capgemini.sprint1.Sprint1.entities.Client;
import com.capgemini.sprint1.Sprint1.entities.Complaint;
import com.capgemini.sprint1.Sprint1.entities.Engineer;
import com.capgemini.sprint1.Sprint1.entities.Product;
import com.capgemini.sprint1.Sprint1.exceptions.ProductNotFoundException;
import com.capgemini.sprint1.Sprint1.repositories.AdminRepository;
import com.capgemini.sprint1.Sprint1.repositories.ClientRepository;
import com.capgemini.sprint1.Sprint1.repositories.ComplaintRepository;
import com.capgemini.sprint1.Sprint1.repositories.EngineerRepository;
import com.capgemini.sprint1.Sprint1.repositories.ProductRepository;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AdminServiceImplTest {

	@InjectMocks
	private AdminServiceImpl service;
	
	@Mock
	private AdminRepository adminRepository;
	
	@Mock
	private EngineerRepository engineerRepository;
	
	@Mock
	private ComplaintRepository complaintRepository;
	
	@Mock
	private ProductRepository productRepository;
	
	@Mock
	private ClientRepository clientRepository;
	
	
	Admin admin;
	Engineer eng1;
	Engineer eng2;
	Client client1;
	Client client2;
	Complaint comp1;
	Complaint comp2;
	Product prod1;
	Product prod2;
	
	
	
	@BeforeEach
	void setUp() throws Exception
	{
//		admin=new Admin("admin",5698474,"abc@gmail.com");
		comp2=new Complaint( "complaint1");
		comp1=new Complaint("complaint2");
//		eng2=new Engineer(3L,"Ram","abc",true,List.of(comp1));
		eng1=new Engineer("Sam","xyz");
		client2=new Client("uyt","Delhi",897456);
	client1=new Client("hjk","Kerala",4455);
	//	prod1=new Product(1, "AC", "Home", null, 1, null, false, List.of(comp1));
//		prod2=new Product("TV","HomeAppliances",2);
		
		
	}
	
	@Test
	@DisplayName("Get Clients")
	public void getClientsTest() {
		
		List<Client> client= List.of(client1,client2);
		when(clientRepository.findAll()).thenReturn(client);
		assertEquals(client,service.getClients());
	}
	
	@Test
	@DisplayName("Get Complaints")
	public void getComplaints() {
		
		List<Complaint> complaint= List.of(comp1,comp2);
		when(complaintRepository.findAll()).thenReturn(complaint);
		assertEquals(complaint,service.getAllComplaints());
	}
	
	
	
	
	
	
	
	
	@Test
	@DisplayName("Get ComplaintsByProducts")
	public void getComplaintsByProducts() throws ProductNotFoundException  {
		
//		when(productRepository.findById(prod1.getModelNumber())).thenReturn(Optional.of(prod1));
//	when(complaintRepository.existsById(prod1.getModelNumber())).thenReturn(true);
//		when(complaintRepository.findById(prod1.getModelNumber())).thenReturn(Optional.of(comp1));
//		assertEquals(comp1,service.getComplaintOfProduct(prod1.getModelNumber()));
	}

	

	

}