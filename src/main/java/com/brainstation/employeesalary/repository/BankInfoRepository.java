package com.brainstation.employeesalary.repository;

import com.brainstation.employeesalary.entity.BankInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankInfoRepository extends CrudRepository<BankInfo, Long> {
}
