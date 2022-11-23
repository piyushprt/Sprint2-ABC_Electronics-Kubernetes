package com.capgemini.sprint1.Sprint1.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Engineer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long employeeId;
	@ToString.Exclude
	private String password;
	private String engineerName;
	private boolean active;
	
	@OneToMany(mappedBy = "engineer",cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
//	@JoinColumn
	@JsonIgnore
	private List<Complaint> complaintList;
	
	public Engineer(String engineerName,String password) {
		this.engineerName = engineerName;
		this.password = password;
		this.active = true;
	}
}
