package com.capgemini.sprint1.Sprint1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.sprint1.Sprint1.entities.Engineer;

@Repository
public interface EngineerRepository extends JpaRepository<Engineer, Long> {
	
	@Query(value = "select * from engineer where employee_id = (select employee_id from complaint where complaint_id = ?1)", nativeQuery = true)
	public Engineer getEngineerByComplaintId(long complaintId);
	
	@Query (value = "select * from engineer where engineer.employee_id in (select e.employee_id from engineer e join complaint c on c.employee_id = e.employee_id where c.product_model_number = ?1)", nativeQuery = true)
	public List<Engineer> getEngineerByProductId(long modelNumber);
}
