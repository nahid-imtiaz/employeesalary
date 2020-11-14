package com.brainstation.employeesalary.service;

import com.brainstation.employeesalary.entity.Grade;
import com.brainstation.employeesalary.repository.EmployeeGradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    EmployeeGradeRepository employeeGradeRepository;

    @Override
    public float calculateBasicSalary(int lowestBasic, int multiplier) {

        float basicSal = lowestBasic + (multiplier * 5000);
        return basicSal;
    }

    @Override
    public float calculateTotalSalary(float basicSal) {
        float houseRentPercentage = (float) ((20 / 100.0) * basicSal);
        float medicalInPercentage = (float) ((15 / 100.0) * basicSal);
        return basicSal + houseRentPercentage + medicalInPercentage;
    }

    public Grade createOrUpdateGrade(Grade entity) {

        Optional<Grade> employeeGrade = employeeGradeRepository.findByRankName(entity.getRankName());

        if (employeeGrade.isPresent()) {
            Grade newEntity = employeeGrade.get();
            newEntity.setBasicSalary(entity.getBasicSalary());
            newEntity.setTotalSalary(entity.getTotalSalary());

            newEntity = employeeGradeRepository.save(newEntity);

            return newEntity;
        } else {
            entity = employeeGradeRepository.save(entity);

            return entity;
        }

    }
}
