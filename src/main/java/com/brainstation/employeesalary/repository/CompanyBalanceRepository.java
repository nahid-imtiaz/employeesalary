package com.brainstation.employeesalary.repository;


import com.brainstation.employeesalary.entity.CompanyAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyBalanceRepository extends CrudRepository<CompanyAccount, String> {
    Optional<CompanyAccount> findByAccountName(String accountName);

}
