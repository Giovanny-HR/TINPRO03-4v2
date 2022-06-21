/*
Giovanny Marchena
1021941
TI1A
 */

import java.util.*;
import java.util.concurrent.atomic.*;

public class Administrative {

    //Collection
    Set<Student> studentList = new HashSet<>();

    //Constructor
    public Administrative(){
        students();
    }
    public void students(){
        studentList.add(new Student("1021941", "Giovanny Marchena", Gender.MALE,"Technische Informatica", "TI1A"));
        studentList.add(new Student("1647853", "Sydney Aüstria", Gender.FEMALE,"Technische Informatica", "TI1A"));
        studentList.add(new Student("1458627", "Kevin Hapsburg", Gender.MALE,"Technische Informatica", "TI1A"));
        studentList.add(new Student("1643785", "Victoria Kozmekov", Gender.FEMALE,"Technische Informatica", "TI1A"));
        studentList.add(new Student("0965488", "Paula Stealer", Gender.FEMALE,"Technische Informatica", "TI1A"));
    }

    public void studentsWithAcquiredField(String moduleCode){
        Set<Student> passed = new HashSet<>();
        Set<Student> notPassed = new HashSet<>();
        //---CHECK---
        studentList.forEach(student -> student.courseList.stream()
                .filter(course -> {
                    if(course.isCoursePassed()){
                        passed.add(student);
                    }
                    else{
                        notPassed.add(student);
                    }
                    return false;
                    //---CHECK---
                }));
        System.out.println("The following students have obtained " + moduleCode + " course ");
        passed.forEach(student -> System.out.println("Name: " + student.getStudentName() + " ||  Grade: " + student.getGrade(moduleCode)));
//        System.out.println("----------------------------------------------------------------------");
        System.out.println("\n The following students ave not obtained " + moduleCode + " course");
        notPassed.forEach(student -> System.out.println("Name: " + student.getStudentName() + " || Grade: " + student.getGrade(moduleCode)));
    }

    public void toBeCompletedCourses(String studentNumber){
        System.out.println("\n Student: " + studentNumber + " have not obtained the following courses:");
        studentList.stream()
                //---CHECK---
                .filter(student -> student.getStudentNumber().equalsIgnoreCase(studentNumber))
                .forEach(student -> student.getNotPassedCourses()
                        .forEach(course -> System.out.println(course.getModuleCode())));
    }


    public void completedCourse(String studentNumber) {
        System.out.println("\nStudent: " + studentNumber + " have obtained the following courses:");
//        System.out.println("----------------------------------------------------------------------");
        studentList.stream()
                .filter(student -> student.getStudentNumber().equalsIgnoreCase(studentNumber))
                .forEach(student -> student.getNotPassedCourses()
                        .forEach(course -> System.out.println(course.getModuleCode())));

//        System.out.println("---------------------------------------------------------------------- \n");
    }

    public void averageGradeCourse(String  moduleCode){
        List<Double> adding = new ArrayList<>();

        studentList.forEach(student -> student.courseList.stream()
                .filter(course -> course.getModuleCode().equalsIgnoreCase(moduleCode))
                .forEach(course ->{
                    if(student.getGrade(moduleCode) > 0)adding.add(student.getGrade(moduleCode));
                })
        );
        double average = Math.round(adding.stream()
                .mapToDouble(Double::doubleValue)
                .sum()/ adding.size()*100.0)/100.0;
        System.out.println("Average course: " + moduleCode + " || " + average);
    }

    public void sumGradeCourse(String studentNumber){
        List<Double>adding = new ArrayList<>();

        studentList.stream()
                .filter(student -> student.getStudentNumber().equalsIgnoreCase(studentNumber))
                .forEach(student  -> student.courseList
                                .forEach(course  -> {
                    adding.add(course.getGrade());
                })
                );
        double sum = Math.round(adding.stream()
                .mapToDouble(Double::doubleValue)
                .sum() * 100.0)/100.0;
        System.out.println("Sum of all grades for all courses fo student: " + studentNumber + " || " + sum);
    }

    public void averageMaleFemale(String moduleCode) {
        //---CHECK---
        Set<Double> males = new HashSet<>();
        List<Double> females = new ArrayList<>();
        studentList
                .forEach(student -> student.courseList.stream()
                        .filter(vak -> vak.getModuleCode().equalsIgnoreCase(moduleCode))
                        .forEach(vak -> {
                            if (student.getGender().equals(Gender.MALE)) {
                                males.add(student.getGrade(moduleCode));

                            } else if (student.getGender().equals(Gender.FEMALE)) {
                                females.add(student.getGrade(moduleCode));
                            }
                        }));

        double somMales = Math.round(males.stream()
                .mapToDouble(Double::doubleValue)
                .sum() / males.size() * 100.0) / 100.0;

        double somFemales = Math.round(females.stream()
                .mapToDouble(Double::doubleValue)
                .sum() / females.size());
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Average of Males: " + somMales + " || Avergae of Females: " + somFemales);
        if (somMales > somFemales) {
            System.out.println("Men scored better than women.");
        } else if (somMales < somFemales) {
            System.out.println("Women have outperformed men.");
        } else if (somMales == somFemales) {
            System.out.println("Men and women scored equally high");
        } else {
            System.out.println("Something went wrong in calculating the average between men and women");
        }
    }

    public void variance(String moduleCode) {
        //---CHECK---
        double[] grade = new double[studentList.size()];
        AtomicInteger counter = new AtomicInteger();
        studentList
                .forEach(student -> student.courseList.stream()
                        .filter(course -> course.getModuleCode().equalsIgnoreCase(moduleCode))
                        .forEach(course -> {
                            grade[counter.get()] = course.getGrade();
                            counter.getAndIncrement();
                        }));
        System.out.println("Variance for: " + moduleCode + " || " + variance(grade) + "\n");
    }

    public void standardVariance(String studentNumber) {
        //---CHECK---
        double[] grade = new double[studentList.size()];
        AtomicInteger counter = new AtomicInteger();
        studentList.stream()
                .filter(student -> student.getStudentNumber().equalsIgnoreCase(studentNumber))
                .forEach(student -> student.courseList
                        .forEach(course -> {
                            grade[counter.get()] = course.getGrade();
                            counter.getAndIncrement();
                        }));
        System.out.println("----------------------------------------------------------------------");
        System.out.println("The standard deviance for  student " + studentNumber + " || " + standardDeviace(grade) + "\n");
    }

    public double variance(double[] waarden) {
        //---CHECK---
        double variance;
        double average = calculateAverge(waarden);

        System.out.println("Average: " + average);

        for (int i = 0; i < waarden.length; i++) {
            if (waarden[i] != 0) {
                waarden[i] = Math.pow(waarden[i] - average, 2);
            }
        }
        variance = calculateAverge(waarden);

        return Math.round(variance * 100.0) / 100.0;
    }

    public double standardDeviace(double[] waarden) {
        return Math.round(Math.sqrt(variance(waarden)) * 100.0) / 100.0;
    }
    public double calculateAverge(double[] waarden) {
        //---CHECK---
        AtomicReference<Double> average = new AtomicReference<>(0.0);
        AtomicInteger total = new AtomicInteger();
        Arrays.stream(waarden).forEach(waarde -> {
            average.updateAndGet(v -> v + waarde);
            total.getAndIncrement();

        });

        return Math.round(average.get() / total.get() * 100.0) / 100.0;

    }
}


//---CHECK---