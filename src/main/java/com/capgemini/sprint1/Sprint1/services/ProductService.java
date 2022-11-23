package com.capgemini.sprint1.Sprint1.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capgemini.sprint1.Sprint1.entities.Complaint;
import com.capgemini.sprint1.Sprint1.entities.Engineer;
import com.capgemini.sprint1.Sprint1.entities.Product;
import com.capgemini.sprint1.Sprint1.exceptions.ProductNotFoundException;

@Service
public interface ProductService {
	
	public Product addProduct(Product product);

	public List<Product> getProducts();
	//new
	public List<Product> getAvailableProducts();

	public void removeProduct(long modelNumber) throws ProductNotFoundException;

	public void updateProductWarrenty(long modelNumber,long warrentyYears) throws ProductNotFoundException;
	
	public List<Engineer> getEngineersByProductId(long modelNumber) throws ProductNotFoundException;
	
	public List<Complaint> getProductComplaints(long modelNumber) throws ProductNotFoundException;
}
