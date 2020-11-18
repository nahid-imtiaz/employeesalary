package com.brainstation.employeesalary.controller;

import com.brainstation.employeesalary.dto.EmployeeSalaryInfo;
import com.brainstation.employeesalary.entity.CompanyAccount;
import com.brainstation.employeesalary.entity.Employee;
import com.brainstation.employeesalary.repository.CompanyBalanceRepository;
import com.brainstation.employeesalary.repository.EmployeeRepository;
import com.brainstation.employeesalary.service.BankInfoService;
import com.brainstation.employeesalary.service.CompanyBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class CompanyBalanceController {
    @Autowired
    CompanyBalanceService companyBalanceService;
    @Autowired
    CompanyBalanceRepository companyBalanceRepository;
    @Autowired
    BankInfoService bankInfoService;
    @Autowired
    EmployeeRepository employeeRepository;

    @RequestMapping(value = "/deposit-balance", method = RequestMethod.GET)
    public String getDepositCompanyBalanceForm() {
        return "deposit-company-balance";
    }

    @RequestMapping(value = "/deposit-balance", method = RequestMethod.POST)
    public String setCompanyBalance(@RequestParam(value = "deposit_amt") String depositAmt,
                                    Model model) {

        Optional<CompanyAccount> companyInfo = companyBalanceRepository.findByAccountName("Brain-station-23");
        companyBalanceService.saveOrUpdateCompanyAccount(companyInfo, depositAmt);
        model.addAttribute("message", "Company Balance Added");
        return "deposit-company-balance";
    }

    @RequestMapping(path = "/salary-disburse-list", method = RequestMethod.GET)
    public String getSalaryInfo(Model model) {
        List<EmployeeSalaryInfo> list = bankInfoService.getAllBankInfo();

        model.addAttribute("employees", list);
        return "disburse-salary";
    }

    @RequestMapping(value = "/disburse-salary", method = RequestMethod.POST)
    public String disburseSalary(Model model) {
        List<Employee> employeeList = (List<Employee>) employeeRepository.findAll();
        CompanyAccount companyAccount = companyBalanceRepository.findByAccountName("Brain-station-23").get();
        double totalSalary = companyBalanceService.getTotalEmployeeSalary(employeeList);
        double totalDeposit = companyAccount.getCurrentBalance();

        if (totalSalary > totalDeposit) {
            return "deposit-company-balance";
        } else {
            companyBalanceService.disburseAmount(employeeList, companyAccount);
            model.addAttribute("companyInfo", companyAccount);
            return "total-paid-remain-balance";
        }

    }
}
