package com.capgemini.sprint1.Sprint1.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.sprint1.Sprint1.entities.Complaint;
import com.capgemini.sprint1.Sprint1.entities.Engineer;
import com.capgemini.sprint1.Sprint1.exceptions.EngineerNotFoundException;
import com.capgemini.sprint1.Sprint1.exceptions.InvalidLoginCredentials;
import com.capgemini.sprint1.Sprint1.repositories.ComplaintRepository;
import com.capgemini.sprint1.Sprint1.repositories.EngineerRepository;

@Service
public class EngineerServiceImpl implements EngineerService {
	
	@Autowired
	private EngineerRepository engineerRepository;
	
	@Autowired
	private ComplaintRepository complaintRepository;
	
	@Override
	public boolean login(long userId, String password) throws Exception {
		Optional<Engineer> engineer = engineerRepository.findById(userId);
		
		if(engineer.isPresent()) {
			String pass = engineer.get().getPassword();
			
			if(pass.equals(password)) {
				return true;
			} else {
				throw new InvalidLoginCredentials();
			}
		} else {
			throw new EngineerNotFoundException();
		}
	}

	@Override
	public Engineer getEngineer(long employeeId) throws EngineerNotFoundException {
		if(engineerRepository.existsById(employeeId)) {
			Engineer getEngineer = engineerRepository.findById(employeeId).get();
			return getEngineer;
		} else {
			throw new EngineerNotFoundException();
		}
		
	}
	
	@Override
	public List<Engineer> getAllEngineers() {
		List<Engineer> allEngineers = engineerRepository.findAll();
		return allEngineers;
	}

	@Override
	public List<Complaint> getAllOpenComplaints(long employeeId) {
		boolean exists = engineerRepository.existsById(employeeId);
		
		if(exists) {
			List<Complaint> complaints = complaintRepository.findByEngineerEmployeeId(employeeId);
			return complaints
					.stream()
					.filter(c -> "open".equals(c.getStatus()))
					.collect(Collectors.toList());
		}
		
		return null;
	}

	@Override
	public List<Complaint> getResolvedComplaints(long employeeId) {
		List<Complaint> complaints = complaintRepository.findByEngineerEmployeeId(employeeId);
		return complaints
				.stream()
				.filter(c -> "resolved".equals(c.getStatus()))
				.collect(Collectors.toList());
	}

	
	
	

}
