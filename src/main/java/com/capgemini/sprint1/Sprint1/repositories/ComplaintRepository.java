package com.capgemini.sprint1.Sprint1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.sprint1.Sprint1.entities.Complaint;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
	
	List<Complaint> findByEngineerEmployeeId(long employeeId);
	
	List<Complaint> findByClientClientId(long clientId);
	
	List<Complaint> findByProductModelNumber(long modelNumber);
}
