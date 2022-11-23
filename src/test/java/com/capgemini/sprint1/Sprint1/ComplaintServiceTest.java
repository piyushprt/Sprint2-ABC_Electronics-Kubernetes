//package com.capgemini.sprint1.Sprint1;
//
//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import com.capgemini.sprint1.Sprint1.exceptions.ClientNotFoundException;
//import com.capgemini.sprint1.Sprint1.exceptions.ComplaintNotFoundException;
//import com.capgemini.sprint1.Sprint1.exceptions.ProductOutOfWarrentyException;
//import com.capgemini.sprint1.Sprint1.services.ClientService;
//import com.capgemini.sprint1.Sprint1.services.ComplaintService;
//
//
//@DisplayName("Complaint Test Cases")
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@SpringBootTest
//class ComplaintServiceTest {
//
//	@Autowired
//	ComplaintService service;
//	
//	@Autowired
//	ClientService clientService;
//
//
//	@Test
//	@Order(1)
//	@DisplayName("Status set to resolved")
//	public void changeStatusTest() throws ComplaintNotFoundException {
//
//		Assertions.assertEquals("The complaint is resolved", service.changeComplaintStatus(35));
//
//	}
//
//	@Test
//	@Order(2)
//	@DisplayName("Status set to open")
//	public void changeStatusTest2() throws ComplaintNotFoundException {
//		// Resolved
//		Assertions.assertEquals("The complaint is open", service.changeComplaintStatus(35));
//
//	}
//
//	@Test
//	@DisplayName("Client Login")
//	public void clientLoginTest() throws Exception {
//		clientService.login(11, "address1");
//	}
//
//	@Test
//	@DisplayName("Getting All Client Complaints")
//	public void getClientAllComplaints() throws ClientNotFoundException {
//		service.getClientAllComplaints(11);
//
//	}
//
//	@Test
//	@DisplayName("Getting All Client open Complaints")
//	public void getClientAllOpenComplaintsTest() throws ClientNotFoundException {
//		// System.out.println(service.getClientAllOpenComplaints(1));
//		service.getClientOpenComplaints(11);
//	}
//
//	@Test
//	@DisplayName("Getting Engineer by ClientId")
//	public void getEngineerTest() throws ComplaintNotFoundException {
//
//		// System.out.println(service.getEngineer(1));
//		service.getEngineer(1);
//	}
//
//	@Test
//	@DisplayName("Getting Product")
//	public void getProductTest() throws ComplaintNotFoundException {
//
//		// System.out.println(service.getEngineer(1));
//		service.getProduct(21);
//	}
//
//	@Test
//	@DisplayName("Invalid Client Login")
//	public void invalidClientLoginTest() {
//		assertThrows(ClientNotFoundException.class, () -> {
//			clientService.login(1, "Wrong Pass");
//		});
//	}
//
//	@Test
//	@DisplayName("Check if out of warranty error is thrown")
//	public void warrantyTest() {
//		Assertions.assertThrows(ProductOutOfWarrentyException.class, () -> {
//			service.bookComplaint(11, 30, "Not Working..");
//		});
//
//	}
//
//}
