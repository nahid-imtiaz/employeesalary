package com.brainstation.employeesalary.service;

import com.brainstation.employeesalary.entity.Employee;
import com.brainstation.employeesalary.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository repository;
	
	public List<Employee> getAllEmployees()
	{
		List<Employee> result = (List<Employee>) repository.findAll();
		
		if(result.size() > 0) {
			return result;
		} else {
			return new ArrayList<Employee>();
		}
	}
	
	public Employee getEmployeeById(Long id)
	{
		Optional<Employee> employee = repository.findById(id);
		
		if(employee.isPresent()) {
			return employee.get();
		}
		return null;
	}
	

	public Employee createEmployee(Employee entity){
		if (entity != null){
			entity =repository.save(entity);
		}
		return entity;
	}

}