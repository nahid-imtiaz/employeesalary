package com.brainstation.employeesalary.repository;

import com.brainstation.employeesalary.entity.BankInfo;
import com.brainstation.employeesalary.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankInfoRepository extends CrudRepository<BankInfo, Long> {
    Optional<BankInfo> findByEmployee(Employee employee);
}
