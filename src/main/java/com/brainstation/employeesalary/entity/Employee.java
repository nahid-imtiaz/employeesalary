package com.brainstation.employeesalary.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="EMPLOYEES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="name")
    private String name;

    @Column(name="address", length=200)
    private String address;

	@Column(name="mobile")
	private String mobile;

	@OneToOne(fetch = FetchType.LAZY,
			cascade =  CascadeType.ALL)
	private Grade grade;

}