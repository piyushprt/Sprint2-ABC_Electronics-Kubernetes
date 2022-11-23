//package com.capgemini.sprint1.Sprint1;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.capgemini.sprint1.Sprint1.entities.Admin;
//import com.capgemini.sprint1.Sprint1.entities.Client;
//import com.capgemini.sprint1.Sprint1.entities.Engineer;
//import com.capgemini.sprint1.Sprint1.entities.Product;
//import com.capgemini.sprint1.Sprint1.exceptions.ProductNotFoundException;
//import com.capgemini.sprint1.Sprint1.exceptions.ProductOutOfWarrentyException;
//import com.capgemini.sprint1.Sprint1.services.AdminService;
//import com.capgemini.sprint1.Sprint1.services.ClientService;
//import com.capgemini.sprint1.Sprint1.services.ComplaintService;
//import com.capgemini.sprint1.Sprint1.services.ProductService;
//
//
//
//@DisplayName("Tests for inserting data into tables")
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@SpringBootTest
//public class InsertionTests {
//
//	@Autowired
//	ClientService clientService;
//
//	@Autowired
//	ProductService productService;
//
//	@Autowired
//	AdminService adminService;
//
//	@Autowired
//	ComplaintService complaintService;
//
//	@Test
//	@Order(6)
//	@DisplayName("Adding Admin")
//	public void addAdmin() {
//		Admin admin = new Admin("Admin Pass", 1234567890, "E1 pass");
//
//		adminService.saveAdmin(admin);
//	}
//
//	@Test
//	@Order(2)
//	@DisplayName("Adding Clients")
//	public void addClientTest() {
//		Client client1 = new Client("C1pass", "address1", 1234);
//		Client client2 = new Client("C2pass", "address2", 2134);
//		Client client3 = new Client("C3pass", "address3", 3234);
//		Client client4 = new Client("C4pass", "address4", 1234);
//		Client client5 = new Client("C5pass", "address5", 5234);
//		Client client6 = new Client("C6pass", "address6", 6234);
//		Client client7 = new Client("C7pass", "address7", 7234);
//		Client client8 = new Client("C8pass", "address8", 8234);
//		Client client9 = new Client("C9pass", "address9", 9234);
//		Client client10 = new Client("C10pass", "address10", 1324);
//
//		clientService.addClient(client1);
//		clientService.addClient(client2);
//		clientService.addClient(client3);
//		clientService.addClient(client4);
//		clientService.addClient(client5);
//		clientService.addClient(client6);
//		clientService.addClient(client7);
//		clientService.addClient(client8);
//		clientService.addClient(client9);
//		clientService.addClient(client10);
//	}
//
//	@Test
//	@Order(1)
//	@DisplayName("Adding Engineers")
//	public void addEngineerTest() {
//		Engineer Engineer1 = new Engineer("E1", "E1 pass");
//		Engineer Engineer2 = new Engineer("E2", "E2 pass");
//		Engineer Engineer3 = new Engineer("E3", "E3 pass");
//		Engineer Engineer4 = new Engineer("E4", "E4 pass");
//		Engineer Engineer5 = new Engineer("E5", "E5 pass");
//		Engineer Engineer6 = new Engineer("E6", "E6 pass");
//		Engineer Engineer7 = new Engineer("E7", "E7 pass");
//		Engineer Engineer8 = new Engineer("E8", "E8 pass");
//		Engineer Engineer9 = new Engineer("E9", "E9 pass");
//		Engineer Engineer10 = new Engineer("E10", "E10 pass");
//
//		adminService.addEngineer(Engineer1);
//		adminService.addEngineer(Engineer2);
//		adminService.addEngineer(Engineer3);
//		adminService.addEngineer(Engineer4);
//		adminService.addEngineer(Engineer5);
//		adminService.addEngineer(Engineer6);
//		adminService.addEngineer(Engineer7);
//		adminService.addEngineer(Engineer8);
//		adminService.addEngineer(Engineer9);
//		adminService.addEngineer(Engineer10);
//	}
//
//	@Test
//	@Order(3)
//	@DisplayName("Adding Products")
//	public void addProductTest1() {
//		Product product1 = new Product("Mobiles", "Samsung", 4);
//		Product product2 = new Product("Laptops", "HP", 5);
//		Product product3 = new Product("Computers", "Dell", 2);
//		Product product4 = new Product("Printers", "Canon", 1);
//		Product product5 = new Product("Mobiles", "Vivo", 4);
//		Product product6 = new Product("Laptops", "Lenovo", 3);
//		Product product7 = new Product("Mobiles", "Redmi", 2);
//		Product product8 = new Product("Laptops", "Asus", 4);
//		Product product9 = new Product("Printers", "Epson", 5);
//		Product product10 = new Product("Mobiles", "Apple", 3);
//		Product product11 = new Product("Mobiles", "Blackberry", 2);
//		Product productOutOfWarranty = new Product("Mobiles", "Vivo", 0);
//
//		productService.addProduct(product1);
//		productService.addProduct(product2);
//		productService.addProduct(product3);
//		productService.addProduct(product4);
//		productService.addProduct(product5);
//		productService.addProduct(product6);
//		productService.addProduct(product7);
//		productService.addProduct(product8);
//		productService.addProduct(product9);
//		productService.addProduct(product10);
//		productService.addProduct(product11);
//		productService.addProduct(productOutOfWarranty);
//	}
//
//	@Test
//	@Order(5)
//	@DisplayName("Booking  complaint")
//	public void bookComplaintTest() throws ProductOutOfWarrentyException, ProductNotFoundException {
//		complaintService.bookComplaint(11, 21, "HELP");
//		complaintService.bookComplaint(12, 22, "PLEASE FIX THIS ISUUE");
//		complaintService.bookComplaint(13, 23, "HELP");
//		complaintService.bookComplaint(14, 24, "PLEASE FIX THIS ISUUE");
//		complaintService.bookComplaint(15, 25, "PLEASE FIX THIS ISUUE");
//		complaintService.bookComplaint(16, 26, "HELP");
//		complaintService.bookComplaint(17, 27, "PLEASE FIX THIS ISUUE");
//		complaintService.bookComplaint(18, 28, "PLEASE HELP");
//	}
//
//	@Test
//	@Order(4)
//	@DisplayName("Adding Products to Clients")
//	public void clientAddProductTest() throws Exception {
//		clientService.buyProduct(11, 21);
//		clientService.buyProduct(12, 22);
//		clientService.buyProduct(13, 23);
//		clientService.buyProduct(14, 24);
//		clientService.buyProduct(15, 25);
//		clientService.buyProduct(16, 26);
//		clientService.buyProduct(17, 27);
//		clientService.buyProduct(18, 28);
//		clientService.buyProduct(19, 29);
//	}
//}