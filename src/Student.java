/*
Giovanny Marchena
1021941
TI1A
 */

import java.util.*;
import java.util.concurrent.atomic.*;


public class Student {

    //Fields
    private String studentName,fieldOfStudy, group;
    private String studentNumber; //why not int? 0 can not be an int
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


    public void fillCourses(){

        courseList.add(new Course("TINTES01-1",1 ));
        courseList.add(new Course("TINPRO03-1",1 ));
        courseList.add(new Course("TINPR04-1",1 ));
        courseList.add(new Course("TINDTB02-1",1 ));
        courseList.add(new Course("TINSEC04-1",1 ));
//        courseList.add(new Course("TINHIS03-1",1 ));
        //Iterates through all the course in the list and assigns each one a random grade
        courseList.forEach(course -> setGrade(randomGrade(), course.getModuleCode()));//-

    }

    //Set grade
    public void setGrade(double grade, String moduleCode){
        courseList.stream()
                .filter(course -> course.getModuleCode()
                .equalsIgnoreCase(moduleCode)) //Compares two strings, ignoring lower case and upper case differences.
                .forEach(course -> {
                    course.setGrade(grade);
                    //Set grade to pass if greater than 5.5, else course not passed
                    if (grade >= 5.5){
                        passedCourses.add(course);
                    }
                    else{
                        notPassedCourses.add(course);
                    }
                });
    }


    public double getGrade(String moduleCode){
        //An object reference that may be updated atomically
        //Gets an  object reference to grade which is updated automatically
        AtomicReference<Double> grade = new AtomicReference<>();//Synchronization
        courseList.stream()
                .filter(course ->  course.getModuleCode().equalsIgnoreCase(moduleCode))
                .forEach(course ->{
                    grade.set(course.getGrade());
                });
//        System.out.println(grade.get());
        return grade.get();//return current value
    }

    //Method to create grades
    private double randomGrade(){
         return (double) ( (int) (((Math.random() * 10) + 1) * 10)) / 10; //You receive a nice 2 digits
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
