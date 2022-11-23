package com.capgemini.sprint1.Sprint1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.sprint1.Sprint1.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	@Query(value = "select * from product where model_number = (select product_model_number from complaint where complaint_id = ?1);", nativeQuery = true)
	public Product getProductByComplaintId(long complaintId);
	
}
