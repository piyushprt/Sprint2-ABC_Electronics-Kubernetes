package com.capgemini.sprint1.Sprint1.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
public class Complaint {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long complaintId;
	private String complaintName;
	private String status;
	
	public Complaint(String complaintName) {
		this.complaintName = complaintName;
		this.status = "open";
	}
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id", referencedColumnName = "clientId")
	@JsonIgnore
	private Client client;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id", referencedColumnName = "employeeId")
	@JsonIgnore
	private Engineer engineer;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "product_model_number", referencedColumnName = "modelNumber")
	@JsonIgnore
	private Product product;
	
}
