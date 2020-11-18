package com.brainstation.employeesalary.controller;

import com.brainstation.employeesalary.entity.Grade;
import com.brainstation.employeesalary.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class GradeController {
    @Autowired
    GradeService gradeService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getEmployeesGradSettingForm() {
        return "grade-setting";
    }

    @RequestMapping(path = "/set-all-grade", method = RequestMethod.POST)
    public String setAllGrade(@RequestParam(value = "bsalary", required = true) String bsalary,
                              Model model) {

        int lowestSalary = Integer.parseInt(bsalary);
        int multiplier = 0;
        for (int i = 6; i >= 1; i--) {

            float basicSal = gradeService.calculateBasicSalary(lowestSalary, multiplier);
            float totalSal = gradeService.calculateTotalSalary(basicSal);

            String rankNmae = "Grade" + i;
            Grade grade = new Grade();
            grade.setBasicSalary(basicSal);
            grade.setTotalSalary(totalSal);
            grade.setRankName(rankNmae);
            gradeService.createOrUpdateGrade(grade);

            multiplier++;

        }
        model.addAttribute("message", "All Grade created.");
        return "grade-setting";
    }
}
