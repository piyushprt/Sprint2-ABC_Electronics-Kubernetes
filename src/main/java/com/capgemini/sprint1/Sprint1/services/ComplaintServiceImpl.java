package com.capgemini.sprint1.Sprint1.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.sprint1.Sprint1.entities.Engineer;
import com.capgemini.sprint1.Sprint1.entities.Complaint;
import com.capgemini.sprint1.Sprint1.entities.Product;
import com.capgemini.sprint1.Sprint1.exceptions.ClientNotFoundException;
import com.capgemini.sprint1.Sprint1.exceptions.ComplaintNotFoundException;
import com.capgemini.sprint1.Sprint1.exceptions.ProductNotFoundException;
import com.capgemini.sprint1.Sprint1.exceptions.ProductOutOfWarrentyException;
import com.capgemini.sprint1.Sprint1.repositories.ClientRepository;
import com.capgemini.sprint1.Sprint1.repositories.ComplaintRepository;
import com.capgemini.sprint1.Sprint1.repositories.EngineerRepository;
import com.capgemini.sprint1.Sprint1.repositories.ProductRepository;

@Service
public class ComplaintServiceImpl implements ComplaintService {
	
	@Autowired
	private ComplaintRepository complaintRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private EngineerRepository engineerRepository;

	@Override
	@Transactional
	public String bookComplaint(long clientId, long modelNumber, String complaintName) throws ProductOutOfWarrentyException, ProductNotFoundException {
		List<Engineer> engineers = engineerRepository.findAll();
		Random rand = new Random();

		List<Engineer> activeEngineers = engineers.stream().
				filter(x-> x.isActive()).
				collect(Collectors.toList());

		Engineer randomEngineer = activeEngineers.
				get(rand.nextInt(activeEngineers.size()));
		
		Optional<Product> getProduct = productRepository.findById(modelNumber);

		if(!getProduct.isPresent()) {
			throw new ProductNotFoundException();
		}
		
		Product product = getProduct.get();
		if (product.getWarrentyDate().compareTo(LocalDate.now()) <= 0) {
			throw new ProductOutOfWarrentyException();
		}

		Complaint complaint = new Complaint();
		complaint.setComplaintName(complaintName);
		complaint.setClient(clientRepository.findById(clientId).get());
		complaint.setProduct(product);
		complaint.setEngineer(randomEngineer);
		complaint.setStatus("open");

		complaintRepository.save(complaint);
		return "Complaint is Registered";
	}

	@Override
	public List<Complaint> getComplaints(long clientId) throws ClientNotFoundException {
		if(clientRepository.existsById(clientId)) {
			List<Complaint> complaints = clientRepository.findById(clientId).get().getComplaintList();
			return complaints;
		} else {
			throw new ClientNotFoundException();
		}
	}

	@Override
	public String changeComplaintStatus(long complaintId) throws ComplaintNotFoundException {
		if(complaintRepository.existsById(complaintId)) {
			Complaint complaint = complaintRepository.findById(complaintId).get();
			String status = complaint.getStatus();
			status = "open".equals(status) ? "resolved" : "open";
			complaint.setStatus(status);
			complaintRepository.save(complaint);
			return "The complaint is " + status;
		} else {
			throw new ComplaintNotFoundException();
		}
	}

	@Override
	public List<Complaint> getClientAllComplaints(long clientId) throws ClientNotFoundException {
		if(clientRepository.existsById(clientId)) {
			List<Complaint> complaints = complaintRepository.findByClientClientId(clientId);
			return complaints;
		}else {
			throw new ClientNotFoundException();
		}
	}

	@Override
	public List<Complaint> getClientOpenComplaints(long clientId) throws ClientNotFoundException {
		if(clientRepository.existsById(clientId)) {
			List<Complaint> openComplaints = getClientAllComplaints(clientId)
					.stream()
					.filter(x->x.getStatus().equals("open"))
					.collect(Collectors.toList());
			return openComplaints;
		}
		return null;
	}

	@Override
	public List<Complaint> getClientResolvedComplaints(long clientId) throws ClientNotFoundException {
		if(clientRepository.existsById(clientId)) {
			List<Complaint> openComplaints = getClientAllComplaints(clientId)
					.stream()
					.filter(x->x.getStatus().equals("resolved"))
					.collect(Collectors.toList());
			return openComplaints;
		}
		return null;
	}

	@Override
	public Engineer getEngineer(long complaintId) throws ComplaintNotFoundException {
		if(complaintRepository.existsById(complaintId)) {
			Engineer engineer = engineerRepository.getEngineerByComplaintId(complaintId);
			return engineer;
		} else {
			throw new ComplaintNotFoundException();
		}
	}

	@Override
	public Product getProduct(long complaintId) throws ComplaintNotFoundException {
		if(complaintRepository.existsById(complaintId)) {
			Product product = productRepository.getProductByComplaintId(complaintId);
			return product;
		}else {
			throw new ComplaintNotFoundException();
		}
	}

}
