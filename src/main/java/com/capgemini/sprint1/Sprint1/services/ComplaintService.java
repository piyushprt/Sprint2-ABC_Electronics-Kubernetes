package com.capgemini.sprint1.Sprint1.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capgemini.sprint1.Sprint1.entities.Complaint;
import com.capgemini.sprint1.Sprint1.entities.Engineer;
import com.capgemini.sprint1.Sprint1.entities.Product;
import com.capgemini.sprint1.Sprint1.exceptions.ClientNotFoundException;
import com.capgemini.sprint1.Sprint1.exceptions.ComplaintNotFoundException;
import com.capgemini.sprint1.Sprint1.exceptions.ProductNotFoundException;
import com.capgemini.sprint1.Sprint1.exceptions.ProductOutOfWarrentyException;

@Service
public interface ComplaintService {
	
	public String bookComplaint(long clientId, long modelNumber, String complaintName) throws ProductOutOfWarrentyException, ProductNotFoundException;
	
	public String changeComplaintStatus(long complaintId) throws ComplaintNotFoundException;
	
	public List<Complaint> getClientAllComplaints(long clientId) throws ClientNotFoundException;
	
	public List<Complaint> getClientOpenComplaints(long clientId) throws ClientNotFoundException;
	
	public List<Complaint> getClientResolvedComplaints(long clientId) throws ClientNotFoundException;
	
	public List<Complaint> getComplaints(long clientId) throws ClientNotFoundException;

	public Engineer getEngineer(long complaintId) throws ComplaintNotFoundException;
	
	public Product getProduct(long complaintId) throws ComplaintNotFoundException;
}
