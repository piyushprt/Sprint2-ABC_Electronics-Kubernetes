//package com.capgemini.sprint1.Sprint1;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import com.capgemini.sprint1.Sprint1.entities.Product;
//import com.capgemini.sprint1.Sprint1.exceptions.ProductNotFoundException;
//import com.capgemini.sprint1.Sprint1.repositories.ProductRepository;
//import com.capgemini.sprint1.Sprint1.services.ProductService;
//
//@DisplayName("Product Test Cases")
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@SpringBootTest
//class ProductServiceTest {
//	
//	@MockBean
//	ProductRepository productRepository;
//
//	@Autowired
//	ProductService productService;
//	
//	Product product;
//	Product product2;
//	Product product3;
//	Product product4;
//	List<Product> products;
//	List<Product> availableList;
//	
//	@BeforeEach
//	public void initialize() {
//		product = new Product(1, "AC", "Home", LocalDate.now(), 2, null, false, null);
//		product2 = new Product(2, "TV", "Electronics", LocalDate.now(), 1, null, true, null);
//		product3 = new Product(3, "FD", "Electronics", LocalDate.now(), 1, null, false, null);
//		product4 = new Product(4, "KM", "Home", LocalDate.now(), 2, null, true, null);
//		
//		products = List.of(product,product2,product3,product4);
//		availableList = List.of(product2,product4);
//	}
//	
//	@Test
//	public void addProductTest() {	
//		Mockito.when(productRepository.save(product2)).thenReturn(product2);
//		
//		Mockito.doReturn(Optional.of(product)).when(productRepository).findById(product.getModelNumber());
//		
//		assertThat(productService.addProduct(product2)).isEqualTo(product2);
//	}
//	
//	@Test
//	public void getProductTest() {
//		List<Product> productList = new ArrayList<>();
//		System.out.println(product);
//		System.out.println(productRepository.save(product));
//		Mockito.when(productRepository.save(product)).thenReturn(product);
//		Mockito.when(productRepository.save(product2)).thenReturn(product2);
//		Mockito.when(productRepository.save(product3)).thenReturn(product3);
//		Mockito.when(productRepository.findAll()).thenReturn(productList);
//
////		assertThat(productServiceImpl.getProducts()).isEqualTo(productList);
//		assertEquals(products, productService.getProducts());
//	}
//
////	@Test
////	@DisplayName("get assigned engineers")
////	public void getEngineersTest() throws ProductNotFoundException {
////		productService.getEngineersByProductId(21);
////
////	}
////
////	@Test
////	@DisplayName("get Product complaints")
////	public void getProductComplaintsTest() throws ProductNotFoundException {
////		productService.getProductComplaints(21);
////
////	}
////
////	@Test
////	@DisplayName("get all Product")
////	public void getProductsTest() {
////		productService.getProducts();
////
////	}
////
////	
////	@Test
////	@DisplayName("Remove Product")
////	public void removeProductTest() throws ProductNotFoundException {
////		productService.removeProduct(28);
////	}
////	
////	
////	@Test
////	@DisplayName("Invalid get assigned engineers")
////	public void InvalidGetEngineersTest() {
////		Assertions.assertThrows(ProductNotFoundException.class, () -> {
////			productService.getEngineersByProductId(100000);
////		});
////	}
////
////	@Test
////	@DisplayName("Invalid get Product complaints")
////	public void InvalidGetProductComplaintsTest() {
////		Assertions.assertThrows(ProductNotFoundException.class, () -> {
////			productService.getProductComplaints(100000);
////		});
////	}
////
////	@Test
////	@DisplayName("Invalid Test Invalid model number while remove product")
////	public void invalidRemoveTest() {
////
////		Assertions.assertThrows(ProductNotFoundException.class, () -> {
////			productService.removeProduct(100000);
////		});
////
////	}
//	
//	
//
//}
