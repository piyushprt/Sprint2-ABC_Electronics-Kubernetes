//package com.capgemini.sprint1.Sprint1;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import com.capgemini.sprint1.Sprint1.exceptions.ProductNotFoundException;
//import com.capgemini.sprint1.Sprint1.services.EngineerService;
//
//@DisplayName("Engineer Test Cases")
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@SpringBootTest
//class EngineerServiceTest {
//	
//	@Autowired
//	EngineerService service;
//
//	@Test
//	@DisplayName("All Open Complaints")
//	void getAllOpenComplaints() throws ProductNotFoundException {
//		service.getAllOpenComplaints(10);
//		
//	}
//	@Test
//	@DisplayName("All Resolved Complaints")
//	public void getResolvedComplaints() {
//		service.getResolvedComplaints(10);
//	}
//	
//
//}
