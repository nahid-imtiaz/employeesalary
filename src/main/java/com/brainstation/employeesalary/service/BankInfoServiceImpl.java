package com.brainstation.employeesalary.service;

import com.brainstation.employeesalary.dto.EmployeeSalaryInfo;
import com.brainstation.employeesalary.entity.BankInfo;
import com.brainstation.employeesalary.repository.BankInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.LinkedList;
import java.util.List;

@Service
public class BankInfoServiceImpl implements BankInfoService {

    @Autowired
    BankInfoRepository bankInfoRepository;

    @Override
    public BankInfo createBankInfo(BankInfo entity) {
        if (entity != null){
            entity =bankInfoRepository.save(entity);
        }
        return entity;
    }

    public List<EmployeeSalaryInfo> getAllBankInfo()
    {
        List<BankInfo> result = (List<BankInfo>) bankInfoRepository.findAll();
        List<EmployeeSalaryInfo> emplist = new LinkedList<>();
        for (BankInfo bankInfo : result){

            EmployeeSalaryInfo employeeSalaryInfo = new EmployeeSalaryInfo();

            employeeSalaryInfo.setBankAccountName(bankInfo.getAccountName());
            employeeSalaryInfo.setName(bankInfo.getEmployee().getName());
            double basicSalary = bankInfo.getEmployee().getGrade().getBasicSalary();
            double totalSalary = bankInfo.getEmployee().getGrade().getTotalSalary();
            employeeSalaryInfo.setBasicSalary(String.valueOf(basicSalary));
            employeeSalaryInfo.setTotalsalary(String.valueOf(totalSalary));
            employeeSalaryInfo.setRank(bankInfo.getEmployee().getGrade().getRankName());

            emplist.add(employeeSalaryInfo);

        }

       return emplist;
    }
}