package com.capgemini.sprint1.Sprint1.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capgemini.sprint1.Sprint1.entities.Complaint;
import com.capgemini.sprint1.Sprint1.entities.Engineer;
import com.capgemini.sprint1.Sprint1.exceptions.EngineerNotFoundException;

@Service
public interface EngineerService {
	
	public List<Complaint> getAllOpenComplaints(long employeeId);
	
	public List<Engineer> getAllEngineers();
	
	public List<Complaint> getResolvedComplaints(long employeeId);
	
	public Engineer getEngineer(long id) throws EngineerNotFoundException;

	public boolean login(long userId, String password) throws Exception;
}
