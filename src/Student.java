/*
Giovanny Marchena
1021941
TI1A
 */

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;


public class Student {
    //FIELDS
    private String studentName,fieldOfStudy, group;
    private String studentNumber;
    private Gender gender;

    //Course Collection
    List<Course> courseList = new LinkedList<>();
    Set<Course> passedCourses = new HashSet<>();
    Set<Course>notPassedCourses = new HashSet<>();

    //Constructor
    public Student(String studentNumber, String studentName, Gender gender,String fieldOfStudy, String group){
    this.studentNumber = studentNumber;
    this.studentName = studentName;
    this.gender = gender;
    this.fieldOfStudy = fieldOfStudy;
    this.group = group;
    fillCourses();
    }

    //Method with subjects in the corresponding field of study
    public void fillCourses(){
        //---CHECK---
        courseList.add(new Course("TINTES01-1",1 ));
        courseList.add(new Course("TINPRO03-1",1 ));
        courseList.add(new Course("TINPR04-1",1 ));
        courseList.add(new Course("TINDTB02-1",1 ));
        courseList.add(new Course("TINSEC04-1",1 ));
        courseList.forEach(course -> setGrade(randomGrade(), course.getModuleCode()));
        //---CHECK---
    }

    //Method of Grade for Course
    public void setGrade(double grade, String moduleCode){
        //---CHECK---
        courseList.stream().filter(course -> course.getModuleCode().equalsIgnoreCase(moduleCode))
                .filter(course ->{
                    course.setGrade(grade);
                    if (grade >= 5.5){
                        passedCourses.add(course);
                    }
                    else{
                        notPassedCourses.add(course);
                    }
                    return false; //---CHECK---
                });
        //---CHECK---
    }

    //Method to get Grade
    public double getGrade(String moduleCode){
        //---CHECK---
       //An object reference that may be updated atomically
        AtomicReference<Double> grade = new AtomicReference<>();
        courseList.stream()
                .filter(course ->  course.getModuleCode().equalsIgnoreCase(moduleCode))
                .forEach(course ->{
                    grade.set(course.getGrade());
                });
        //---CHECK---
        return 0; //---CHECK---
    }

    //Method to create grades
    private double randomGrade(){
        double randG = (double) ((int) (((Math.random() * 10) + 1) * 10)) / 10;
        return randG; //-
    }

    //Getters & Setters


    public Gender getGender() {
        return gender;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    //Return List of Passed Courses
    public Set<Course> getPassedCourses(){
        return passedCourses;
    }

    public Set<Course> getNotPassedCourses() {
        return notPassedCourses;
    }

    public List<Course> getCourseList() {
        return courseList;
    }
}
