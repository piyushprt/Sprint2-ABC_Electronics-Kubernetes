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
import com.capgemini.sprint1.Sprint1.exceptions.ClientNotFoundException;
import com.capgemini.sprint1.Sprint1.exceptions.ComplaintNotFoundException;
import com.capgemini.sprint1.Sprint1.exceptions.InvalidLoginCredentials;
import com.capgemini.sprint1.Sprint1.exceptions.ProductNotFoundException;
import com.capgemini.sprint1.Sprint1.exceptions.ProductOutOfWarrentyException;
import com.capgemini.sprint1.Sprint1.login.UserLogin;
import com.capgemini.sprint1.Sprint1.services.ComplaintService;

@RestController
@RequestMapping("/complaint")
public class ComplaintController {
	
	@Autowired
	private ComplaintService complaintService;
	
	private boolean checkSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			UserLogin currentUser = (UserLogin) session.getAttribute("userDetails");
			if (currentUser.isClient()) {
				return true;
			}
			return false;
		} catch (NullPointerException e) {
			return false;
		}
	}
	
	@GetMapping("/book/{modelNumber}/{complaintName}")
	public ResponseEntity<String> registerComplaint(@PathVariable long modelNumber, @PathVariable String complaintName, HttpServletRequest request) throws InvalidLoginCredentials, ProductOutOfWarrentyException, ProductNotFoundException{
		boolean validLogin = checkSession(request);

		if (!validLogin) {
			throw new InvalidLoginCredentials();
		}
		HttpSession session = request.getSession();
		UserLogin currentUser = (UserLogin) session.getAttribute("userDetails");
		
		String getStatus = complaintService.bookComplaint(currentUser.getUserId(), modelNumber, complaintName);
		return new ResponseEntity<String>(getStatus,HttpStatus.OK);
	}
	
	@GetMapping("/complaints")
	public ResponseEntity<List<Complaint>> getComplaints(HttpServletRequest request) throws InvalidLoginCredentials, ClientNotFoundException {
		boolean validLogin = checkSession(request);

		if (!validLogin) {
			throw new InvalidLoginCredentials();
		}
		HttpSession session = request.getSession();
		UserLogin currentUser = (UserLogin) session.getAttribute("userDetails");
		
		List<Complaint> complaints = complaintService.getComplaints(currentUser.getUserId());
		return new ResponseEntity<List<Complaint>>(complaints,HttpStatus.OK);
	}
	
	@GetMapping("/changeStatus/{complaintId}")
	public ResponseEntity<String> changeComplaintStatus(@PathVariable long complaintId,HttpServletRequest request) throws InvalidLoginCredentials, ComplaintNotFoundException {
		boolean validLogin = checkSession(request);

		if (!validLogin) {
			throw new InvalidLoginCredentials();
		}
		
		String status = complaintService.changeComplaintStatus(complaintId);
		return new ResponseEntity<String>(status,HttpStatus.OK);
	}
	
	@GetMapping("/get/all")
	public ResponseEntity<List<Complaint>> getClientAllComplaints(HttpServletRequest request) throws InvalidLoginCredentials, ClientNotFoundException {
		boolean validLogin = checkSession(request);

		if (!validLogin) {
			throw new InvalidLoginCredentials();
		}
		HttpSession session = request.getSession();
		UserLogin currentUser = (UserLogin) session.getAttribute("userDetails");
		List<Complaint> complaints = complaintService.getClientAllComplaints(currentUser.getUserId());
		return new ResponseEntity<List<Complaint>>(complaints,HttpStatus.OK);
	}
	
	@GetMapping("/get/open")
	public ResponseEntity<List<Complaint>> getClientOpenComplaints(HttpServletRequest request) throws InvalidLoginCredentials, ClientNotFoundException {
		boolean validLogin = checkSession(request);

		if (!validLogin) {
			throw new InvalidLoginCredentials();
		}
		HttpSession session = request.getSession();
		UserLogin currentUser = (UserLogin) session.getAttribute("userDetails");
		
		List<Complaint> openComplaints = complaintService.getClientOpenComplaints(currentUser.getUserId());
		return new ResponseEntity<List<Complaint>>(openComplaints,HttpStatus.OK);
	}
	
	@GetMapping("/get/resolved")
	public ResponseEntity<List<Complaint>> getClientResolvedComplaints(HttpServletRequest request) throws InvalidLoginCredentials, ClientNotFoundException {
		boolean validLogin = checkSession(request);

		if (!validLogin) {
			throw new InvalidLoginCredentials();
		}
		HttpSession session = request.getSession();
		UserLogin currentUser = (UserLogin) session.getAttribute("userDetails");
		List<Complaint> resolvedComplaints = complaintService.getClientResolvedComplaints(currentUser.getUserId());
		return new ResponseEntity<List<Complaint>>(resolvedComplaints,HttpStatus.OK);
	}
	
	@GetMapping("/get/engineer/{complaintId}")
	public ResponseEntity<Engineer> getEngineerByComplaintId(@PathVariable long complaintId, HttpServletRequest request) throws InvalidLoginCredentials, ComplaintNotFoundException {
		boolean validLogin = checkSession(request);

		if (!validLogin) {
			throw new InvalidLoginCredentials();
		}
		
		Engineer engineer = complaintService.getEngineer(complaintId);
		return new ResponseEntity<Engineer>(engineer,HttpStatus.FOUND);
	}
	
	@GetMapping("/get/product/{complaintId}")
	public ResponseEntity<Product> getProductByComplaintId(@PathVariable long complaintId,HttpServletRequest request) throws InvalidLoginCredentials, ComplaintNotFoundException {
		boolean validLogin = checkSession(request);

		if (!validLogin) {
			throw new InvalidLoginCredentials();
		}
		Product product = complaintService.getProduct(complaintId);
		return new ResponseEntity<Product>(product,HttpStatus.FOUND);
	}
}
