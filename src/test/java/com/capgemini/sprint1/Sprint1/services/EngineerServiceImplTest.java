package com.capgemini.sprint1.Sprint1.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.capgemini.sprint1.Sprint1.entities.Complaint;
import com.capgemini.sprint1.Sprint1.entities.Engineer;
import com.capgemini.sprint1.Sprint1.exceptions.EngineerNotFoundException;
import com.capgemini.sprint1.Sprint1.repositories.ComplaintRepository;
import com.capgemini.sprint1.Sprint1.repositories.EngineerRepository;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class EngineerServiceImplTest {
	
	@InjectMocks
	private EngineerServiceImpl service;
	
	@Mock
	private EngineerRepository engineerRepository;
	
	@Mock
	private ComplaintRepository complaintRepository;
	
	Engineer engineer;
	Engineer engineer2;
	Complaint complaint;
	Complaint complaint2;
	Complaint complaint3;

	@BeforeEach
	void setUp() throws Exception {
		complaint = new Complaint(1L, "complaint1", "open", null, engineer, null);
		complaint2 = new Complaint(2L, "complaint2", "open", null, engineer, null);
		complaint3 = new Complaint(3L, "comaplaint3", "resolved", null, engineer, null);
		engineer = new Engineer(1L ,"stud", "eng1", true, List.of(complaint,complaint2,complaint3));
		engineer2 = new Engineer(2L, "stud", "eng2", false, null);
	}

	@Test
	@DisplayName("Get Open Complaints")
	void testGetAllOpenComplaints() {
		List<Complaint> complaints = List.of(complaint,complaint2,complaint3);
		List<Complaint> openComplaints = List.of(complaint,complaint2);
		
		when(engineerRepository.existsById(engineer.getEmployeeId())).thenReturn(true);
		when(complaintRepository.findByEngineerEmployeeId(engineer.getEmployeeId())).thenReturn(complaints);

		assertEquals(openComplaints, service.getAllOpenComplaints(engineer.getEmployeeId()));
		
	}
	
	@Test
	@DisplayName("Get all Engineers")
	void testGetAllEngineers() {
		List<Engineer> engineersList = List.of(engineer,engineer2);
		
		when(engineerRepository.findAll()).thenReturn(engineersList);
		
		assertEquals(engineersList, service.getAllEngineers());
	}
	
	@Test
	@DisplayName("Get all resolved complaints")
	void testGetAllResolvedComplaints() {
		List<Complaint> complaints = List.of(complaint,complaint2,complaint3);
		List<Complaint> resolvedComplaints = List.of(complaint3);
		
		when(engineerRepository.existsById(engineer.getEmployeeId())).thenReturn(true);
		when(complaintRepository.findByEngineerEmployeeId(engineer.getEmployeeId())).thenReturn(complaints);
		
		assertEquals(resolvedComplaints, service.getResolvedComplaints(engineer.getEmployeeId()));
	}
	
	@Test
	@DisplayName("Get Engineer By Id-Exists")
	void testGetEngineerById() throws EngineerNotFoundException {
		when(engineerRepository.save(engineer)).thenReturn(engineer);
		when(engineerRepository.existsById(engineer.getEmployeeId())).thenReturn(true);
		when(engineerRepository.findById(engineer.getEmployeeId())).thenReturn(Optional.of(engineer));
		
		assertEquals(engineer, service.getEngineer(engineer.getEmployeeId()));
	}

	@Test
	@DisplayName("Get Engineer By Id-Not Exists")
	void testGetEngineerById2() {
		when(engineerRepository.save(engineer)).thenReturn(engineer);
		when(engineerRepository.existsById(engineer.getEmployeeId())).thenReturn(true);
		when(engineerRepository.findById(engineer.getEmployeeId())).thenReturn(Optional.of(engineer));
		
		assertThrows(EngineerNotFoundException.class, () -> {
			service.getEngineer(engineer2.getEmployeeId());
		});
	}
}
