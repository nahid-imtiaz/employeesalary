package com.brainstation.employeesalary.controller;

import com.brainstation.employeesalary.entity.Employee;
import com.brainstation.employeesalary.entity.Grade;
import com.brainstation.employeesalary.repository.EmployeeGradeRepository;
import com.brainstation.employeesalary.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/")
public class EmployeeController {
    @Autowired
    EmployeeService service;
    @Autowired
    EmployeeGradeRepository employeeGradeRepository;

    @RequestMapping(path = "/createEmployeeFrom", method = RequestMethod.GET)
    public String getEmployeesForm(Model model) {
        List<Grade> grades = (List<Grade>) employeeGradeRepository.findAll();
        model.addAttribute("grades", grades);
        model.addAttribute("employee", new Employee());
        return "add-edit-employee";
    }

    @RequestMapping(path = "/createEmployee", method = RequestMethod.POST)
    public String createOrUpdateEmployee(@RequestParam(value = "name") String name,
                                         @RequestParam(value = "address") String address,
                                         @RequestParam(value = "mobile") String mobile,
                                         @RequestParam(value = "grade") String grade,
                                         Model model) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setAddress(address);
        employee.setMobile(mobile);
        Optional<Grade> grade1 = employeeGradeRepository.findById(Long.parseLong(grade));
        if (grade1.isPresent()) {
            employee.setGrade(grade1.get());
        }

        service.createEmployee(employee);
        model.addAttribute("message", "Employee created. Please create Employee Bank Information.");
        return "add-edit-employee";

    }

    @RequestMapping(path = "/list-employees", method = RequestMethod.GET)
    public String getAllEmployees(Model model) {
        List<Employee> list = service.getAllEmployees();

        model.addAttribute("employees", list);
        return "list-employees";
    }

}
