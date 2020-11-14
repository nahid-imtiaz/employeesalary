package com.brainstation.employeesalary.repository;

import com.brainstation.employeesalary.entity.Grade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeGradeRepository extends CrudRepository<Grade, Long> {
    Optional<Grade> findByRankName(String rankName);
}
