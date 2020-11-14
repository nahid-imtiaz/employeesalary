package com.brainstation.employeesalary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeSalaryInfo {
    String name;
    String rank;
    String basicSalary;
    String totalsalary;
    String bankAccountName;

}
