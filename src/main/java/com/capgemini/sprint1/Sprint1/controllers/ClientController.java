package com.capgemini.sprint1.Sprint1.controllers;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.sprint1.Sprint1.entities.Client;
import com.capgemini.sprint1.Sprint1.entities.Engineer;
import com.capgemini.sprint1.Sprint1.entities.Product;
import com.capgemini.sprint1.Sprint1.exceptions.ClientNotFoundException;
import com.capgemini.sprint1.Sprint1.exceptions.EngineerNotFoundException;
import com.capgemini.sprint1.Sprint1.exceptions.InvalidLoginCredentials;
import com.capgemini.sprint1.Sprint1.login.UserLogin;
import com.capgemini.sprint1.Sprint1.services.ClientService;
import com.capgemini.sprint1.Sprint1.services.ProductService;

@RestController
@RequestMapping("/client")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private ProductService productService;
	
	private boolean checkSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			UserLogin currentUser = (UserLogin) session.getAttribute("userDetails");
			System.out.println(currentUser);
			if (currentUser.isClient()) {
				return true;
			}
			return false;
		} catch (NullPointerException e) {
			return false;
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> addClient(@RequestBody Client client) {
		clientService.addClient(client);
		return new ResponseEntity<String>("New Client Registered, Please go to /login",HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UserLogin loginDetails, HttpServletRequest request) throws Exception {
		if (clientService.login(loginDetails.getUserId(), loginDetails.getPassword())) {
			HttpSession session = request.getSession(true);
			loginDetails.setClient(true);
			loginDetails.setAdmin(false);
			loginDetails.setEngineer(false);
			session.setAttribute("userDetails", loginDetails);
		} 
		
		return new ResponseEntity<String>("Client Logged In", HttpStatus.FOUND);
	}
	
	@GetMapping("/logout")
	public ResponseEntity<String> signout(HttpServletRequest request) {
		boolean validLogin = checkSession(request);
		if (!validLogin) {
			return new ResponseEntity<String>("Client in not logged In", HttpStatus.FORBIDDEN);
		}

		HttpSession session = request.getSession(true);
		UserLogin loginDetails = (UserLogin) session.getAttribute("userDetails");
		loginDetails.setClient(false);
		session.setAttribute("userDetails", loginDetails);
		return new ResponseEntity<String>("Client Logged Out", HttpStatus.FOUND);
	}
	
	@GetMapping("/get")
	public ResponseEntity<Client> getClients(HttpServletRequest request) throws InvalidLoginCredentials,ClientNotFoundException {
		boolean validLogin = checkSession(request);

		if (!validLogin) {
			throw new InvalidLoginCredentials();
		}
		
		HttpSession session = request.getSession();
		UserLogin currentUser = (UserLogin) session.getAttribute("userDetails");
		return new ResponseEntity<Client>(clientService.getClientById(currentUser.getUserId()), HttpStatus.FOUND);
	}
	
	@GetMapping("/get/engineer/{complaintId}")
	public ResponseEntity<Engineer> getEngineer(@PathVariable long complaintId, HttpServletRequest request) throws InvalidLoginCredentials, ClientNotFoundException, EngineerNotFoundException {
		boolean validLogin = checkSession(request);

		if (!validLogin) {
			throw new InvalidLoginCredentials();
		}
		HttpSession session = request.getSession();
		UserLogin currentUser = (UserLogin) session.getAttribute("userDetails");
		Engineer engineer = clientService.getEngineerByComplaintId(currentUser.getUserId() ,complaintId);
		return new ResponseEntity<Engineer>(engineer,HttpStatus.OK);
	}
	
	@GetMapping("/get/product/available")
	public ResponseEntity<List<Product>> getAvaialableProducts() {
		List<Product> availableProducts = productService.getAvailableProducts();
		return new ResponseEntity<List<Product>>(availableProducts,HttpStatus.OK);
	}
	
	@PutMapping("/buyProduct/{modelNumber}")
	public ResponseEntity<String> addProduct(@PathVariable long modelNumber, HttpServletRequest request) throws Exception {
		boolean validLogin = checkSession(request);

		if (!validLogin) {
			throw new InvalidLoginCredentials();
		}
		
		HttpSession session = request.getSession();
		UserLogin currentUser = (UserLogin) session.getAttribute("userDetails");
		clientService.buyProduct(currentUser.getUserId(), modelNumber);
		return new ResponseEntity<String>("Product with id " + modelNumber + " is owned" , HttpStatus.OK);
	}
	
	
}
