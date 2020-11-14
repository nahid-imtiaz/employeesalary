package com.brainstation.employeesalary.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="Bank_Info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="account_name")
    private String accountName;

    @Column(name="account_type")
    private String accountType;

    @Column(name="account_number")
    private String accountNumber;

    @Column(name="current_balance")
    private float currentBalance;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL)
    private Employee employee;
}
