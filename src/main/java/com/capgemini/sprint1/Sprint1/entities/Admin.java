package com.capgemini.sprint1.Sprint1.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private long adminId;
	@ToString.Exclude
	private String password;
	private long contactNumber;
	private String emailId;
	
	public Admin(String password,long contactNumber,String emailId) {
		this.password = password;
		this.contactNumber = contactNumber;
		this.emailId = emailId;
	}
	
}
