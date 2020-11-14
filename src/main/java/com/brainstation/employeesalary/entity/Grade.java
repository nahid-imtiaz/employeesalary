package com.brainstation.employeesalary.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Entity
@Table(name="Grade")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="rank_name")
    private String rankName;

    @Column(name="basic_sal")
    private float basicSalary;

    @Column(name="total_sal")
    private float totalSalary;

    public Grade(String rankName, float basicSalary, float totalSalary) {
        this.rankName = rankName;
        this.basicSalary = basicSalary;
        this.totalSalary = totalSalary;
    }
}
