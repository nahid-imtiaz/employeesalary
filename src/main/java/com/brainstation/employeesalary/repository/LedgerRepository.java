package com.brainstation.employeesalary.repository;

import com.brainstation.employeesalary.entity.Ledger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LedgerRepository extends CrudRepository<Ledger, Long> {
}
