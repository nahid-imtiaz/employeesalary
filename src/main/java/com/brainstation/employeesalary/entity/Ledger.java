package com.brainstation.employeesalary.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Ledger")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ledger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "to_account")
    private String empAccountName;

    @Column(name = "from_account")
    private String comAccountName;

    @Column(name = "disburse_amount")
    private float disburseAmt;

    @Column
    @Type(type = "date")
    private Date salaryDate;
}
