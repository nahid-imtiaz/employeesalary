package com.brainstation.employeesalary.service;

import com.brainstation.employeesalary.dto.EmployeeSalaryInfo;
import com.brainstation.employeesalary.entity.BankInfo;
import com.brainstation.employeesalary.entity.Employee;

import java.util.List;

public interface BankInfoService {
    public BankInfo createBankInfo(BankInfo entity);
    public List<EmployeeSalaryInfo> getAllBankInfo();
}
