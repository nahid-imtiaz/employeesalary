package com.brainstation.employeesalary.service;

import com.brainstation.employeesalary.entity.CompanyAccount;
import com.brainstation.employeesalary.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface CompanyBalanceService {
    public double getTotalEmployeeSalary(List<Employee> employeeList);
    public void disburseAmount(List<Employee> employeeList, CompanyAccount balance);
    public void saveOrUpdateCompanyAccount(Optional<CompanyAccount> companyInfo, String depositAmt);
}
