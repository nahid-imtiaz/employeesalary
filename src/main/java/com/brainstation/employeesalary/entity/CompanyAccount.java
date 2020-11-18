package com.brainstation.employeesalary.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="Company_Balance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name="account_name")
    private String accountName;

    @Column(name="current_balance")
    private float currentBalance;

    @Column(name="disburse_balance")
    private float disburseBalance;
}
