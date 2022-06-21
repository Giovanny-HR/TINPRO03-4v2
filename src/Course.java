/*
Giovanny Marchena
1021941
TI1A
 */

import java.util.*;

public class Course {
    //Fields
    private String moduleCode;
    private double  grade;
    private int studyYear;
    private boolean coursePass;

    //Contsructor
    public Course (String moduleCode, int studyYear){
        //---CHECK---
        if(studyYear < 1){
            this.studyYear = 1;
        }
        else if (studyYear > 4){
            this.studyYear = 4;
            System.out.println("Study Year: " + studyYear);
        }
        //---CHECK---
        this.moduleCode = moduleCode;
        this.studyYear = studyYear;
    }

    //Getters & Setters
    public String getModuleCode() {
        return moduleCode;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
        //---CHECK---
        if(this.grade > 10.0){
            this.grade = 10.0;
        }else if (this.grade < 1.0){
            this.grade = 1.0;
        }
        setCoursePass(grade >= 5.5);
        //---CHECK---
    }

    public void setCoursePass(boolean coursePass) {
        this.coursePass = coursePass;
    }

    public boolean isCoursePassed(){
        return coursePass;
    }

    //Print Course
    public void printCourseInfo(){
        System.out.println("ModuleCode: " + moduleCode +
                " || " + "Grade: " + grade +
                " || " + "Study-year: " + " || " + studyYear);
    }

}