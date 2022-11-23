package com.capgemini.sprint1.Sprint1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.sprint1.Sprint1.entities.Admin;
import com.capgemini.sprint1.Sprint1.entities.Client;
import com.capgemini.sprint1.Sprint1.entities.Complaint;
import com.capgemini.sprint1.Sprint1.entities.Engineer;
import com.capgemini.sprint1.Sprint1.entities.Product;
import com.capgemini.sprint1.Sprint1.exceptions.ComplaintNotFoundException;
import com.capgemini.sprint1.Sprint1.exceptions.EngineerNotFoundException;
import com.capgemini.sprint1.Sprint1.exceptions.InvalidLoginCredentials;
import com.capgemini.sprint1.Sprint1.exceptions.ProductNotFoundException;
import com.capgemini.sprint1.Sprint1.repositories.AdminRepository;
import com.capgemini.sprint1.Sprint1.repositories.ClientRepository;
import com.capgemini.sprint1.Sprint1.repositories.ComplaintRepository;
import com.capgemini.sprint1.Sprint1.repositories.EngineerRepository;
import com.capgemini.sprint1.Sprint1.repositories.ProductRepository;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private EngineerRepository engineerRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ComplaintRepository complaintRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public boolean login(long adminId, String password) throws InvalidLoginCredentials {
		Optional<Admin> getAdmin = adminRepository.findById(adminId);
		
		if(!getAdmin.isPresent()) {
			throw new InvalidLoginCredentials();
		}
		
		Admin admin = getAdmin.get();
		String pass = admin.getPassword();
		if (!pass.equals(password)) {
			return false;
		}
		return true;
	}

	@Override
	public void saveAdmin(Admin admin) {
		adminRepository.save(admin);
	}

	@Override
	public void addEngineer(Engineer engineer) {
		engineer.setActive(true);
		engineerRepository.save(engineer);
	}
	
	@Override
	public Engineer getEngineer(long employeeId) throws EngineerNotFoundException {
		Optional<Engineer> getEngineerOptional = engineerRepository.findById(employeeId);
		
		if(!getEngineerOptional.isPresent()) {
			throw new EngineerNotFoundException();
		}
		
		Engineer engineer = getEngineerOptional.get();
		return engineer;
	}
	
	@Override
	public void removeEngineer(long employeeId) throws EngineerNotFoundException {
		Optional<Engineer> getEngineer = engineerRepository.findById(employeeId);
		
		if(getEngineer.isPresent()) {
			getEngineer.get().setActive(false);
			engineerRepository.save(getEngineer.get());
		} else {
			throw new EngineerNotFoundException();
		}
		
	}

	@Override
	public List<Client> getClients() {
		List<Client> allClients = clientRepository.findAll();
		return allClients;
	}

	@Override
	public List<Complaint> getAllComplaints() {
		List<Complaint> allComplaints = complaintRepository.findAll();
		return allComplaints;
	}

	@Override
	public Complaint replaceEmployeeFromComplaint(long engineerId, long complaintId) throws ComplaintNotFoundException, EngineerNotFoundException {
		Optional<Complaint> getComplaint = complaintRepository.findById(complaintId);
		
		if(!getComplaint.isPresent()) {
			throw new ComplaintNotFoundException();
		}
		
		Optional<Engineer> getEngineer = engineerRepository.findById(engineerId);
		
		if(!getEngineer.isPresent()) {
			throw new EngineerNotFoundException();
		}
		
		Complaint complaint = getComplaint.get();
		Engineer engineer = getEngineer.get();
		complaint.setEngineer(engineer);
		complaintRepository.save(complaint);
		
		return complaint;
	}

	@Override
	public List<Complaint> getComplaintOfProduct(long modelNumber) throws ProductNotFoundException {
		Optional<Product> getProduct = productRepository.findById(modelNumber);
		
		if(!getProduct.isPresent()) {
			throw new ProductNotFoundException();
		}
		Product product = getProduct.get();
		return product.getComplaintList();
	}

	
}
