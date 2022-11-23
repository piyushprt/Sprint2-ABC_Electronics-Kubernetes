package com.capgemini.sprint1.Sprint1.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.sprint1.Sprint1.entities.Complaint;
import com.capgemini.sprint1.Sprint1.entities.Engineer;
import com.capgemini.sprint1.Sprint1.entities.Product;
import com.capgemini.sprint1.Sprint1.exceptions.ProductNotFoundException;
import com.capgemini.sprint1.Sprint1.repositories.ComplaintRepository;
import com.capgemini.sprint1.Sprint1.repositories.EngineerRepository;
import com.capgemini.sprint1.Sprint1.repositories.ProductRepository;


@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private EngineerRepository engineerRepository;
	
	@Autowired
	private ComplaintRepository complaintRepository;
	
	@Override
	public Product addProduct(Product product) {
		Product savedProduct = productRepository.save(product);
		return savedProduct;
	}


	@Override
	public List<Product> getProducts() {
		List<Product> products = productRepository.findAll();
		System.out.println(products);
		return products;
	}
	

	@Override
	public List<Product> getAvailableProducts() {
		List<Product> products = productRepository.findAll();
		List<Product> availableProducts = products.stream()
				.filter(x->x.isOwned()==false)
				.collect(Collectors.toList());
		return availableProducts;
	}
	
	
	@Override
	public void removeProduct(long modelNumber) throws ProductNotFoundException {
		if(productRepository.existsById(modelNumber)) {
			productRepository.deleteById(modelNumber);
			
		}else {
			throw new ProductNotFoundException();
		}
	}
	
	@Override
	public void updateProductWarrenty(long modelNumber,long warrentyYears) throws ProductNotFoundException {
		Optional<Product> getProduct = productRepository.findById(modelNumber);
		
		if(getProduct.isPresent()) {
			Product product = getProduct.get();
			product.setWarrentyYears(warrentyYears);
			product.setWarrentyDate(
					product.getDateOfPurchase().plusYears(product.getWarrentyYears())
					);
			productRepository.save(product);
		} else {
			throw new ProductNotFoundException();
		}
	}


	@Override
	public List<Engineer> getEngineersByProductId(long modelNumber) throws ProductNotFoundException {
		if(productRepository.existsById(modelNumber)) {
			List<Engineer> engineers = engineerRepository.getEngineerByProductId(modelNumber);
			return engineers;
		} else {
			throw new ProductNotFoundException();
		}
	}


	@Override
	public List<Complaint> getProductComplaints(long modelNumber) throws ProductNotFoundException {
		if(productRepository.existsById(modelNumber)) {
			List<Complaint> complaints = complaintRepository.findByProductModelNumber(modelNumber);
			return complaints;
		} else {
			throw new ProductNotFoundException();
		}
	}


}
