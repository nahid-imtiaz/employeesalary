package com.brainstation.employeesalary.controller;

import com.brainstation.employeesalary.dto.EmployeeSalaryInfo;
import com.brainstation.employeesalary.entity.BankInfo;
import com.brainstation.employeesalary.entity.Employee;
import com.brainstation.employeesalary.repository.EmployeeRepository;
import com.brainstation.employeesalary.service.BankInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class BankController {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    BankInfoService bankInfoService;


    @RequestMapping(path = "/bank-info", method = RequestMethod.GET)
    public String getBankInfoForm(Model model) {   //generate employee list in dropdown.
        List<Employee> employees = (List<Employee>) employeeRepository.findAll();
        model.addAttribute("employees", employees);
        System.out.println("Bank info controller ----");
        return "bank-info";
    }

    @RequestMapping(path = "/createBankInfo", method = RequestMethod.POST)
    public String createBankInfo(@RequestParam(value = "account-name") String accountName,
                                 @RequestParam(value = "account-type") String accountType,
                                 @RequestParam(value = "account-number") String accountNumber,
                                 @RequestParam(value = "employee") String employeeId,
                                 Model model) {
        BankInfo bankInfo = new BankInfo();
        bankInfo.setAccountName(accountName);
        bankInfo.setAccountNumber(accountNumber);
        bankInfo.setAccountType(accountType);

        Optional<Employee> employee1 = employeeRepository.findById(Long.parseLong(employeeId));
        if (employee1.isPresent()) {
            bankInfo.setEmployee(employee1.get());
        }

        bankInfoService.createBankInfo(bankInfo);
        model.addAttribute("message", "Bank Information created");
        return "add-edit-employee";
    }

    @RequestMapping(path = "/salary-info-list", method = RequestMethod.GET)
    public String getAllInfo(Model model) {
        List<EmployeeSalaryInfo> list = bankInfoService.getAllBankInfo();

        model.addAttribute("employees", list);
        return "salary-info-list";
    }

}
