package com.capgemini.sprint1.Sprint1.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.sprint1.Sprint1.entities.Admin;
import com.capgemini.sprint1.Sprint1.entities.Client;
import com.capgemini.sprint1.Sprint1.entities.Complaint;
import com.capgemini.sprint1.Sprint1.entities.Engineer;
import com.capgemini.sprint1.Sprint1.entities.Product;
import com.capgemini.sprint1.Sprint1.exceptions.InvalidLoginCredentials;
import com.capgemini.sprint1.Sprint1.login.UserLogin;
import com.capgemini.sprint1.Sprint1.services.AdminService;
import com.capgemini.sprint1.Sprint1.services.EngineerService;
import com.capgemini.sprint1.Sprint1.services.ProductService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private ProductService productService;

	@Autowired
	private EngineerService engineerService;

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
	
	@PostMapping("/login")
	public ResponseEntity<String> loginAdmin(@RequestBody UserLogin userLogin, HttpServletRequest request) throws Exception {
		if(adminService.login(userLogin.getUserId(), userLogin.getPassword())) {
			HttpSession session = request.getSession(true);
			userLogin.setAdmin(true);
			userLogin.setClient(false);
			userLogin.setEngineer(false);
			session.setAttribute("userDetails", userLogin);
			return new ResponseEntity<String>("Admin logged in", HttpStatus.OK);
		}else {
			throw new InvalidLoginCredentials();
		}
	}
	
	@GetMapping("/logout")
	public ResponseEntity<String> signout(HttpServletRequest request) {
		boolean validLogin = checkSession(request);
		if (!validLogin) {
			return new ResponseEntity<String>("Admin in not logged In", HttpStatus.FORBIDDEN);
		}

		HttpSession session = request.getSession(true);
		UserLogin loginDetails = (UserLogin) session.getAttribute("userDetails");
		loginDetails.setAdmin(false);
		session.setAttribute("userDetails", loginDetails);
		return new ResponseEntity<String>("Admin Logged Out", HttpStatus.FOUND);
	}

	@PostMapping("/add")
	public ResponseEntity<String> saveAdmin(@RequestBody Admin admin, HttpServletRequest request) throws Exception {
//		boolean validLogin = checkSession(request);
//		if (!validLogin) {
//			throw new InvalidLoginCredentials();
//		}

		adminService.saveAdmin(admin);
		return new ResponseEntity<String>("Admin added", HttpStatus.OK);
	}

	@PostMapping("/engineer/add")
	public ResponseEntity<String> addEngineer(@RequestBody Engineer engineer, HttpServletRequest request) throws Exception {
		boolean validLogin = checkSession(request);
		if (!validLogin) {
			throw new InvalidLoginCredentials();
		}
		
		adminService.addEngineer(engineer);
		return new ResponseEntity<String>("Engineer added", HttpStatus.OK);
	}

	@GetMapping("/engineer/getAll")
	public ResponseEntity<List<Engineer>> getAllEngineers(HttpServletRequest request) throws Exception {
		boolean validLogin = checkSession(request);

		if (!validLogin) {
			throw new InvalidLoginCredentials();
		}
		
		List<Engineer> allEngineers = engineerService.getAllEngineers();
		return new ResponseEntity<List<Engineer>>(allEngineers, HttpStatus.OK);
	}

	@GetMapping("/engineer/get/{employeeId}")
	public ResponseEntity<Engineer> getEngineerById(@PathVariable long employeeId, HttpServletRequest request) throws Exception {
		boolean validLogin = checkSession(request);
		if (!validLogin) {
			throw new InvalidLoginCredentials();
		}
		
		Engineer engineer = adminService.getEngineer(employeeId);
		return new ResponseEntity<Engineer>(engineer, HttpStatus.OK);
	}

	@GetMapping("/engineer/remove/{employeeId}")
	public ResponseEntity<String> removeEngineer(@PathVariable long employeeId, HttpServletRequest request) throws Exception {
		boolean validLogin = checkSession(request);
		if (!validLogin) {
			throw new InvalidLoginCredentials();
		}
		
		adminService.removeEngineer(employeeId);
		return new ResponseEntity<String>("Engineer with employeeId " + employeeId + " is removed", HttpStatus.FOUND);
	}

	@GetMapping("/client/getAll")
	public ResponseEntity<List<Client>> getAllClients(HttpServletRequest request) throws Exception {
		boolean validLogin = checkSession(request);

		if (!validLogin) {
			throw new InvalidLoginCredentials();
		}
		
		List<Client> allClients = adminService.getClients();
		return new ResponseEntity<List<Client>>(allClients, HttpStatus.OK);
	}

	@GetMapping("/complaint/getAll")
	public ResponseEntity<List<Complaint>> getAllComplaints(HttpServletRequest request) throws Exception {
		
		boolean validLogin = checkSession(request);

		if (!validLogin) {
			throw new InvalidLoginCredentials();
		}
		
		List<Complaint> allComplaints = adminService.getAllComplaints();
		return new ResponseEntity<List<Complaint>>(allComplaints, HttpStatus.OK);
	}

	@GetMapping("/complaintList/{modelNumber}")
	public ResponseEntity<List<Complaint>> getComplaintForProduct(@PathVariable long modelNumber, HttpServletRequest request) throws Exception {
		boolean validLogin = checkSession(request);

		if (!validLogin) {
			throw new InvalidLoginCredentials();
		}
		
		List<Complaint> complaintsForProduct = adminService.getComplaintOfProduct(modelNumber);
		return new ResponseEntity<List<Complaint>>(complaintsForProduct, HttpStatus.OK);
	}

	@PostMapping("/product/add")
	public ResponseEntity<Product> addProduct(@RequestBody Product product,HttpServletRequest request) throws Exception {
		boolean validLogin = checkSession(request);

		if (!validLogin) {
			throw new InvalidLoginCredentials();
		}
		
		Product savedProduct = productService.addProduct(product);
		return new ResponseEntity<Product>(savedProduct, HttpStatus.OK);
	}

	@DeleteMapping("/product/remove/{modelNumber}")
	public ResponseEntity<String> removeProduct(@PathVariable long modelNumber,HttpServletRequest request) throws Exception {
		boolean validLogin = checkSession(request);

		if (!validLogin) {
			throw new InvalidLoginCredentials();
		}
		
		productService.removeProduct(modelNumber);
		return new ResponseEntity<String>("Product is removed from repository", HttpStatus.FOUND);
	}

	@PatchMapping("/updateWarrenty/{modelNumber}/{warrentyYears}")
	public ResponseEntity<String> updateWarrenty(@PathVariable long modelNumber, @PathVariable long warrentyYears,HttpServletRequest request) throws Exception {
		boolean validLogin = checkSession(request);

		if (!validLogin) {
			throw new InvalidLoginCredentials();
		}
		
		productService.updateProductWarrenty(modelNumber, warrentyYears);
		return new ResponseEntity<String>("Warrenty updated", HttpStatus.FOUND);
	}

	@PatchMapping("/complaint/replace/{complaintId}/{engineerId}")
	public ResponseEntity<String> replaceEmployeeFromComplaint(@PathVariable long engineerId,
			@PathVariable long complaintId, HttpServletRequest request) throws Exception {
		boolean validLogin = checkSession(request);

		if (!validLogin) {
			throw new InvalidLoginCredentials();
		}
		
		adminService.replaceEmployeeFromComplaint(engineerId, complaintId);
		return new ResponseEntity<String>("Employee Replaced", HttpStatus.FORBIDDEN);
	}
}
