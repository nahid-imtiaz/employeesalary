package com.brainstation.employeesalary.repository;

import com.brainstation.employeesalary.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository
        extends CrudRepository<Employee, Long> {

}
