//package com.capgemini.sprint1.Sprint1;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import com.capgemini.sprint1.Sprint1.exceptions.ClientNotFoundException;
//import com.capgemini.sprint1.Sprint1.exceptions.EngineerNotFoundException;
//import com.capgemini.sprint1.Sprint1.exceptions.ProductAlreadyOwnedException;
//import com.capgemini.sprint1.Sprint1.services.ClientService;
//
//
//@DisplayName("Client Test Cases")
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@SpringBootTest
//class ClientServiceTest {
//
//	@Autowired
//	ClientService clientService;
//
//	@Test
//	@Order(1)
//	@DisplayName("Product Add Test")
//	public void clientAddProductTest1() throws Exception {
//		clientService.buyProduct(11, 32);
//	}
//
//	@Test
//	@DisplayName("Client Login")
//	public void clientLoginTest() throws Exception {
//		clientService.login(11, "address1");
//	}
//
//	@Test
//	@DisplayName("Searching Client")
//	public void getClientTest() throws ClientNotFoundException {
//		clientService.getClientById(11);
//	}
//
//	@Test
//	@Order(2)
//	@DisplayName("Invalid Product Add Test")
//	public void invalidClientAddProductTest1() {
//		assertThrows(ProductAlreadyOwnedException.class, () -> {
//			clientService.buyProduct(11, 30);
//		});
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
//	@DisplayName("Searching Client")
//	public void invalidGetClientTest() {
//		assertThrows(ClientNotFoundException.class, () -> {
//			clientService.getClientById(100);
//		});
//	}
//
//	@Test
//	@DisplayName("Searching Engineer")
//	public void invalidGetEngineerByComplaintId() {
//		assertThrows(EngineerNotFoundException.class, () -> {
//			clientService.getEngineerByComplaintId(11,10);
//		});
//	}
//}
