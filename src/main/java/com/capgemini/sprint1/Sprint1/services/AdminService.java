package com.capgemini.sprint1.Sprint1.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capgemini.sprint1.Sprint1.entities.Admin;
import com.capgemini.sprint1.Sprint1.entities.Client;
import com.capgemini.sprint1.Sprint1.entities.Complaint;
import com.capgemini.sprint1.Sprint1.entities.Engineer;
import com.capgemini.sprint1.Sprint1.exceptions.ComplaintNotFoundException;
import com.capgemini.sprint1.Sprint1.exceptions.EngineerNotFoundException;
import com.capgemini.sprint1.Sprint1.exceptions.InvalidLoginCredentials;
import com.capgemini.sprint1.Sprint1.exceptions.ProductNotFoundException;

@Service
public interface AdminService {
	//Login
	boolean login(long adminId, String password) throws InvalidLoginCredentials;
	
	//Admin
	public void saveAdmin(Admin admin);
	
	//Engineer
	public void addEngineer(Engineer engineer);
	
	public Engineer getEngineer(long employeeId) throws EngineerNotFoundException;
	
	public void removeEngineer(long employeeId) throws EngineerNotFoundException;
	
	//Client
	public List<Client> getClients();
	
	//Complaint
	public List<Complaint> getAllComplaints();
	
	public List<Complaint> getComplaintOfProduct(long modelNumber) throws ProductNotFoundException;

	public Complaint replaceEmployeeFromComplaint(long engineerId, long complaintId) throws ComplaintNotFoundException, EngineerNotFoundException;
}
