package com.capgemini.sprint1.Sprint1.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.sprint1.Sprint1.entities.Complaint;
import com.capgemini.sprint1.Sprint1.entities.Engineer;
import com.capgemini.sprint1.Sprint1.exceptions.EngineerNotFoundException;
import com.capgemini.sprint1.Sprint1.exceptions.InvalidLoginCredentials;
import com.capgemini.sprint1.Sprint1.login.UserLogin;
import com.capgemini.sprint1.Sprint1.services.EngineerService;

@RestController
@RequestMapping("/engineer")
public class EngineerController {

	@Autowired
	private EngineerService engineerService;

	
	private boolean checkSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			UserLogin currentUser = (UserLogin) session.getAttribute("userDetails");
			System.out.println(currentUser);
			if (currentUser.isEngineer()) {
				return true;
			}
			return false;
		} catch (NullPointerException e) {
			return false;
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UserLogin loginDetails, HttpServletRequest request) throws Exception {
		if (engineerService.login(loginDetails.getUserId(), loginDetails.getPassword())) {
			HttpSession session = request.getSession(true);
			loginDetails.setEngineer(true);
//			loginDetails.setAdmin(false);
//			loginDetails.setClient(false);
			session.setAttribute("userDetails", loginDetails);
		} 
		
		return new ResponseEntity<String>("Engineer Logged In", HttpStatus.FOUND);
	}
	
	@GetMapping("/logout")
	public ResponseEntity<String> signout(HttpServletRequest request) {
		boolean validLogin = checkSession(request);
		if (!validLogin) {
			return new ResponseEntity<String>("Engineer in not logged In", HttpStatus.FORBIDDEN);
		}

		HttpSession session = request.getSession(true);
		UserLogin loginDetails = (UserLogin) session.getAttribute("userDetails");
		loginDetails.setEngineer(false);
		session.setAttribute("userDetails", loginDetails);
		return new ResponseEntity<String>("Engineer Logged Out", HttpStatus.FOUND);
	}
	
	@GetMapping("/get")
	public ResponseEntity<Engineer> getEngineer(HttpServletRequest request) throws InvalidLoginCredentials, EngineerNotFoundException {
		boolean validLogin = checkSession(request);

		if (!validLogin) {
			throw new InvalidLoginCredentials();
		}
		HttpSession session = request.getSession();
		UserLogin currentUser = (UserLogin) session.getAttribute("userDetails");
		Engineer engineer = engineerService.getEngineer(currentUser.getUserId());
		return new ResponseEntity<Engineer>(engineer,HttpStatus.FOUND);
	}
	
	@GetMapping("/open")
	public ResponseEntity<List<Complaint>> getOpenComplaints(HttpServletRequest request) throws InvalidLoginCredentials {
		boolean validLogin = checkSession(request);

		if (!validLogin) {
			throw new InvalidLoginCredentials();
		}
		HttpSession session = request.getSession();
		UserLogin currentUser = (UserLogin) session.getAttribute("userDetails");
		
		List<Complaint> complaints = engineerService.getAllOpenComplaints(currentUser.getUserId());
		return new ResponseEntity<List<Complaint>>(complaints,HttpStatus.OK);
	}
	
	@GetMapping("/resolved")
	public ResponseEntity<List<Complaint>> getResolvedComplaints(HttpServletRequest request) throws InvalidLoginCredentials {
		boolean validLogin = checkSession(request);

		if (!validLogin) {
			throw new InvalidLoginCredentials();
		}
		HttpSession session = request.getSession();
		UserLogin currentUser = (UserLogin) session.getAttribute("userDetails");
		
		List<Complaint> complaints = engineerService.getResolvedComplaints(currentUser.getUserId());
		return new ResponseEntity<List<Complaint>>(complaints,HttpStatus.OK);
	}
}
