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

import com.capgemini.sprint1.Sprint1.entities.Product;
import com.capgemini.sprint1.Sprint1.exceptions.ProductNotFoundException;
import com.capgemini.sprint1.Sprint1.repositories.ProductRepository;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ProductServiceImplTest {

	@InjectMocks
	private ProductServiceImpl service;

	@Mock
	private ProductRepository repo;

	Product product;
	Product product2;
	Product product3;

	@BeforeEach
	void setUp() {
		product = new Product(1, "AC", "Home", null, 1, null, false, null);
		product2 = new Product(2, "TV", "Home", null, 2, null, true, null);
		product3 = new Product(3, "Laptop", "Home", null, 3, null, false, null);
	}

	@Test
	@DisplayName("Adding Product")
	void testAddProduct() {
		Product newProduct = new Product("TV", "Home", 1);

		when(repo.save(newProduct)).thenReturn(newProduct);

		assertEquals(newProduct, service.addProduct(newProduct));

	}

	@Test
	@DisplayName("Getting List of products")
	void testGetProducts() {
		List<Product> productsList = List.of(product, product2, product3);

		when(repo.findAll()).thenReturn(productsList);

		assertEquals(productsList, service.getProducts());
	}

	@Test
	@DisplayName("Getting list of available products")
	void testGetAvailableProducts() {
		List<Product> productsList = List.of(product, product2, product3);
		List<Product> availableProducts = List.of(product, product3);

		when(repo.findAll()).thenReturn(productsList);

		assertEquals(availableProducts, service.getAvailableProducts());
	}

	@Test
	@DisplayName("Delete Product which not exists")
	void testRemoveProduct1() {
		when(repo.existsById(3L)).thenReturn(false);

		assertThrows(ProductNotFoundException.class, () -> {
			service.removeProduct(3L);
		});
	}
	
	@Test
	@DisplayName("Update Product Warrenty Test")
	void testUpdateProductWarrenty1() {
//		product.setDateOfPurchase(LocalDate.now().plusYears(product.getWarrentyYears()));
//		when(repo.findById(4L)).thenReturn(Optional.of(product));
		
		assertThrows(ProductNotFoundException.class, ()-> {
			service.updateProductWarrenty(4L, 1);
		});
	}
	
	@Test
	@DisplayName("Engineer with Product Id Fail test")
	void testGetEngieerByProductId() {
		when(repo.existsById(3L)).thenReturn(false);
		
		assertThrows(ProductNotFoundException.class, ()-> {
			service.getEngineersByProductId(3L);
		});
	}
	
	@Test
	@DisplayName("Complaint with Product Id Fail test")
	void testGetComplaintByProductId() {
		when(repo.existsById(3L)).thenReturn(false);
		
		assertThrows(ProductNotFoundException.class, ()-> {
			service.getProductComplaints(3L);
		});
	}

	

}
