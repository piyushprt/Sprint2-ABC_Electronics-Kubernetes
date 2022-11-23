package com.capgemini.sprint1.Sprint1.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.sprint1.Sprint1.entities.Complaint;
import com.capgemini.sprint1.Sprint1.entities.Engineer;
import com.capgemini.sprint1.Sprint1.entities.Product;
import com.capgemini.sprint1.Sprint1.exceptions.InvalidLoginCredentials;
import com.capgemini.sprint1.Sprint1.exceptions.ProductNotFoundException;
import com.capgemini.sprint1.Sprint1.login.UserLogin;
import com.capgemini.sprint1.Sprint1.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	private boolean checkSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			UserLogin currentUser = (UserLogin) session.getAttribute("userDetails");
			if (currentUser.isAdmin()) {
				return true;
			}
			return false;
		} catch (NullPointerException e) {
			return false;
		}
	}
	
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProducts(HttpServletRequest request) throws InvalidLoginCredentials {
		boolean validLogin = checkSession(request);
		if (!validLogin) {
			throw new InvalidLoginCredentials();
		}
		
		List<Product> products = productService.getProducts();
		return new ResponseEntity<List<Product>>(products,HttpStatus.FOUND);
	}
	
	@GetMapping("/engineer/get/{modelNumber}")
	public ResponseEntity<List<Engineer>> getEngineersByProductId(@PathVariable long modelNumber,HttpServletRequest request) throws InvalidLoginCredentials, ProductNotFoundException {
		boolean validLogin = checkSession(request);
		if (!validLogin) {
			throw new InvalidLoginCredentials();
		}
		
		List<Engineer> engineers = productService.getEngineersByProductId(modelNumber);
		return new ResponseEntity<List<Engineer>>(engineers,HttpStatus.OK);
	}
	
	@GetMapping("/complaint/get/{modelNumber}")
	public ResponseEntity<List<Complaint>> getComplaintByProductId(@PathVariable long modelNumber,HttpServletRequest request) throws InvalidLoginCredentials, ProductNotFoundException {
		boolean validLogin = checkSession(request);
		if (!validLogin) {
			throw new InvalidLoginCredentials();
		}
		
		List<Complaint> complaints = productService.getProductComplaints(modelNumber);
		return new ResponseEntity<List<Complaint>>(complaints,HttpStatus.OK);
	}
}
