package com.capgemini.sprint1.Sprint1.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long clientId;
	@ToString.Exclude
	private String password;
	private String address;
	private long phoneNumber;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn
	private List<Product> productList;
	
	
	@OneToMany(mappedBy = "client" ,cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Complaint> complaintList;
	
	public Client(String password,String address,long phoneNumber) {
		this.password = password;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

}
