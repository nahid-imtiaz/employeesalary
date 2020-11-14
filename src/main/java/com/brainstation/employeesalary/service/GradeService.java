package com.brainstation.employeesalary.service;


import com.brainstation.employeesalary.entity.Grade;

public interface GradeService {
    public float calculateBasicSalary(int lowestBasic, int multiplier );
    public float calculateTotalSalary(float basicSAl);
    public Grade createOrUpdateGrade(Grade entity);
}
