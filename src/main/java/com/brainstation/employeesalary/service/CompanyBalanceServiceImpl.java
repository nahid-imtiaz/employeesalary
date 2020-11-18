package com.brainstation.employeesalary.service;

import com.brainstation.employeesalary.entity.BankInfo;
import com.brainstation.employeesalary.entity.CompanyAccount;
import com.brainstation.employeesalary.entity.Employee;
import com.brainstation.employeesalary.entity.Ledger;
import com.brainstation.employeesalary.repository.BankInfoRepository;
import com.brainstation.employeesalary.repository.CompanyBalanceRepository;
import com.brainstation.employeesalary.repository.LedgerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyBalanceServiceImpl implements CompanyBalanceService {
    @Autowired
    BankInfoRepository bankInfoRepository;
    @Autowired
    CompanyBalanceRepository companyBalanceRepository;
    @Autowired
    LedgerRepository ledgerRepository;

    public double getTotalEmployeeSalary(List<Employee> employeeList) {
        double totalSalary = employeeList.stream()
                .collect(Collectors.summingDouble(employee -> employee.getGrade().getTotalSalary()));
        return totalSalary;
    }


    public void disburseAmount(List<Employee> employeeList, CompanyAccount balance) {

        for (Employee employee : employeeList) {
            transferAmount(employee, balance);
        }
    }

    @Transactional
    private void transferAmount(Employee employee, CompanyAccount companyAccount) {
        Optional<BankInfo> bankInfo = bankInfoRepository.findByEmployee(employee);
        BankInfo embankAcc = bankInfo.get();
        float empTotalsal = embankAcc.getEmployee().getGrade().getTotalSalary();
        //money sent to Employee Account
        embankAcc.setCurrentBalance(embankAcc.getCurrentBalance() + empTotalsal);
        bankInfoRepository.save(embankAcc);

        //money adjust from company account
        float presentBalance = companyAccount.getCurrentBalance();
        presentBalance = presentBalance - empTotalsal;
        companyAccount.setCurrentBalance(presentBalance);

        float disBurseAmt = companyAccount.getDisburseBalance();
        disBurseAmt = disBurseAmt + empTotalsal;
        companyAccount.setDisburseBalance(disBurseAmt);
        companyBalanceRepository.save(companyAccount);
        createLedger(embankAcc,companyAccount,empTotalsal);

    }

    private void createLedger(BankInfo embankAcc, CompanyAccount companyAccount, float empSalary) {
        Ledger ledger = new Ledger();
        ledger.setEmpAccountName(embankAcc.getAccountName());
        ledger.setComAccountName(companyAccount.getAccountName());
        ledger.setDisburseAmt(empSalary);
        ledger.setSalaryDate(new Date());

        ledgerRepository.save(ledger);

    }

    public void saveOrUpdateCompanyAccount(Optional<CompanyAccount> companyInfo, String depositAmt) {

        CompanyAccount companyAccount = new CompanyAccount();
        if (!companyInfo.isPresent()) {
            companyAccount.setAccountName("Brain-station-23");
            companyAccount.setCurrentBalance(Float.valueOf(depositAmt));
            companyBalanceRepository.save(companyAccount);
        } else {
            companyAccount = companyInfo.get();
            float totalBlance = companyAccount.getCurrentBalance() + Float.valueOf(depositAmt);
            companyAccount.setCurrentBalance(totalBlance);
            companyBalanceRepository.save(companyAccount);
        }

    }

}
