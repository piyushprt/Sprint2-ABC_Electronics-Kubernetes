package com.capgemini.sprint1.Sprint1.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long modelNumber;
	private String productName;
	private String productCategoryName;
	private LocalDate dateOfPurchase ;
	private long warrentyYears;
	private LocalDate warrentyDate;
	private boolean owned;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Complaint> complaintList;
	
	
	public Product(String productName, String productCategoryName, long warrentyYears) {
		super();
		this.productName = productName;
		this.productCategoryName = productCategoryName;
		this.warrentyYears = warrentyYears;
		this.owned = false;
	}

}
