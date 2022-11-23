//package com.capgemini.sprint1.Sprint1;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.boot.test.context.SpringBootTest;
//import com.capgemini.sprint1.Sprint1.exceptions.EngineerNotFoundException;
//import com.capgemini.sprint1.Sprint1.exceptions.InvalidLoginCredentials;
//import com.capgemini.sprint1.Sprint1.exceptions.ProductNotFoundException;
//import com.capgemini.sprint1.Sprint1.services.AdminService;
//
//@DisplayName("Admin Test Cases")
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@SpringBootTest
//public class AdminServiceTest {
//
//	@Autowired
//	AdminService adminService;
//
//	@Test
//	@DisplayName("Admin Login")
//	public void adminLoginTest() throws InvalidLoginCredentials {
//		adminService.login(33, "Admin Pass");
//	}
//
//	@Test
//	@DisplayName("Get Clients")
//	public void getClientsTest() throws EngineerNotFoundException {
//		adminService.getEngineer(2);
//	}
//
//	@Test
//	@DisplayName("Get Complaints")
//	public void getComplaints() {
//		adminService.getAllComplaints();
//	}
//
//	@Test
//	@DisplayName("Get ComplaintsByProducts")
//	public void getComplaintsOfProducts() throws ProductNotFoundException {
//		adminService.getComplaintOfProduct(22);
//	}
//
//	@Test
//	@DisplayName("Invalid Admin Login")
//	public void invalidAdminLoginTest() {
//		assertThrows(InvalidLoginCredentials.class, () -> {
//			adminService.login(1, "admi");
//		});
//	}
//
//	@Test
//	@DisplayName("Check InvalidEmployeeIdException")
//	public void invalidIdTest() {
//		assertThrows(EngineerNotFoundException.class, () -> {
//			adminService.removeEngineer(100000);
//		});
//	}
//
//	@Test
//	@DisplayName("Remove Engineer")
//	public void removeEngineerTest() throws EngineerNotFoundException {
//		adminService.removeEngineer(2);
//	}
//
//}
//
