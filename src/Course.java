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

    //Constructor
    public Course (String moduleCode, int studyYear){
        if(studyYear < 1){
            this.studyYear = 1;// Initialized to 1 , if the year of the course is less than one
            System.out.println("Study Year: " + studyYear);
        }
        else if (studyYear > 4){
            this.studyYear = 4;
            System.out.println("Study Year: " + studyYear);
        }
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
        //Sets the grade to be equal to the value of grade
        this.grade = grade;
        //If grade is greater than 10, then set it bak to 10
        if(this.grade > 10.0){
            this.grade = 10.0;
        }
        //If grade is less than 1, set it back to 1
        else if (this.grade < 1.0){
            this.grade = 1.0;
        }
        setCoursePass(grade >= 5.5);
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