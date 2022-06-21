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
//        studentList.add(new Student("0952413", "Gran Stallone", Gender.MALE,"Technische Informatica", "TI1B"));
    }

    public void studentsWithAcquiredField(String moduleCode){
        //Iterates through the list of students and checks if they have obtained a course with the module code
        Set<Student> passed = new HashSet<>();
        Set<Student> notPassed = new HashSet<>();
        studentList.forEach(student -> student.courseList.stream()
                .filter(course ->course.getModuleCode().equalsIgnoreCase(moduleCode))
                .forEach(course ->{
                    if(course.isCoursePassed()){
                        //adds to set
                        passed.add(student);
                    }
                    else{
                        notPassed.add(student);
                    }
                }));
        //Display the list of students who have obtained a particular module
        System.out.println("The following students have obtained " + moduleCode + " course ");
        passed.forEach(student -> System.out.println("Name: " + student.getStudentName() + " ||  Grade: " + student.getGrade(moduleCode)));
        System.out.println("----------------------------------------------------------------------");

        System.out.println("The following students have not obtained " + moduleCode + " course");
        notPassed.forEach(student -> System.out.println("Name: " + student.getStudentName() + " || Grade: " + student.getGrade(moduleCode)));
        System.out.println("----------------------------------------------------------------------");
    }

    public void toBeCompletedCourses(String studentNumber){
        //Start by printing the student number of a student who has not obtained the following course
        System.out.println("Student: " + studentNumber + " has not obtained the following courses:");
        //Prints out all the courses of that this particular student has not passed.
        studentList.stream()
                .filter(student -> student.getStudentNumber().equalsIgnoreCase(studentNumber))
                //prints out a list of hte student's module code
                .forEach(student -> student.getNotPassedCourses()
                //Prints out each course's module code
                .forEach(course -> System.out.println(course.getModuleCode() + " || " + course.getGrade())));
        System.out.println("---------------------------------------------------------------------- ");
    }


    public void completedCourse(String studentNumber) {
        System.out.println("Student: " + studentNumber + " has obtained the following courses:");
//        System.out.println("----------------------------------------------------------------------");
        //Prints out a list of all courses that the student has passed and those they have failed
        studentList.stream()
                //Filter the list of students by checking if the student number is equal to the given number
                .filter(student -> student.getStudentNumber().equalsIgnoreCase(studentNumber))
                //Iterates through each course that they have passed by printing its module code
                .forEach(student -> student.getPassedCourses()
                        .forEach(course -> System.out.println(course.getModuleCode() + " || " + course.getGrade())));
        System.out.println("---------------------------------------------------------------------- ");
    }

    public void averageGradeCourse(String  moduleCode){
        //Calculate the average grade for each course in a module
        List<Double> adding = new ArrayList<>();

        //Iterates through the list of students and for each student,
        //filters out all courses that have a module code that matches the current module code
        studentList.forEach(student -> student.courseList.stream()
                .filter(course -> course.getModuleCode().equalsIgnoreCase(moduleCode))
                .forEach(course ->{
                    if(student.getGrade(moduleCode) > 0)adding.add(student.getGrade(moduleCode));
                })
        );
        //For each course in the filtered list, it calculates an average grade
        //by adding up all grade given to students who took the course and dividing by how many students tool the course
        double average = Math.round(adding.stream()
                .mapToDouble(Double::doubleValue)//Returns a doubleStream of the results of applying the given function to the elements of this stream
                .sum()/ adding.size()*100.0)/100.0;
        //Print out both the average grade & which module was taken
        System.out.println("Average course: " + moduleCode + " || " + average);
        System.out.println("---------------------------------------------------------------------- ");
    }

    public void sumGradeCourse(String studentNumber){
        //Calculates the sum of all grades for all courses for a student
        List<Double>adding = new ArrayList<>();//Creates a list of doubles

        studentList.stream()
                .filter(student -> student.getStudentNumber().equalsIgnoreCase(studentNumber))//Only include the student with the same number as the input
                //Iterates through each course and adds up all o its grades
                .forEach(student  -> student.courseList
                                .forEach(course  -> {
                    adding.add(course.getGrade());
                })
                );
        double sum = Math.round(adding.stream()
                .mapToDouble(Double::doubleValue)
                .sum() * 100.0)/100.0;//Takes sum and divides by 100 to get a percentage
        System.out.println("Sum of all grades for all courses for student: " + studentNumber + " || " + sum);
        System.out.println("---------------------------------------------------------------------- ");
    }

    public void averageMaleFemale(String moduleCode) {
        //Determine if the average of male is higher than the average of females.
        Set<Double> males = new HashSet<>();
        List<Double> females = new ArrayList<>();
        //Iterates through the studentList, filtering out all,
        //students who have a moduleCode that is equal to "moduleCode
        studentList
                .forEach(student -> student.courseList.stream()
                        .filter(vak -> vak.getModuleCode().equalsIgnoreCase(moduleCode))
                        //Checks if they are male or female
                        .forEach(vak -> {
                            //if male, adds their grade from the course with moduleCode as oe of its grades
                            if (student.getGender().equals(Gender.MALE)) {
                                males.add(student.getGrade(moduleCode));

                            }
                            //If female, adds their grade frm the course with moduleCode as one of its grades
                            else if (student.getGender().equals(Gender.FEMALE)) {
                                females.add(student.getGrade(moduleCode));
                            }
                        }));

        //Sum is used on each stream to calculate the total number of male and female objects
        double somMales = Math.round(males.stream()
                .mapToDouble(Double::doubleValue)//Maps each student to a double value
                .sum() / males.size() * 100.0) / 100.0;//Calculates hoe many males there are divided by female size

        double somFemales = Math.round(females.stream()
                .mapToDouble(Double::doubleValue)
                .sum() / females.size());

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
        System.out.println("----------------------------------------------------------------------");
    }

    public void variance(String moduleCode) {
//        List<Double>grade = new ArrayList<>();
        double[] grade = new double[studentList.size()];//Stores a list of all the grades for each course in the studentList
//        AtomicInteger provides atomic operations such as incrementing
//        or decrementing without affecting other variables' int its scope
        AtomicInteger counter = new AtomicInteger();
        studentList
                .forEach(student -> student.courseList.stream()
                        .filter(course -> course.getModuleCode().equalsIgnoreCase(moduleCode))
                        .forEach(course -> {
                            grade[counter.get()] = course.getGrade();
//                            grade.set(counter.get(), course.getGrade());
                            //Increments counter by 1 vefore moving on to the next course
                            counter.getAndIncrement();
                        }));
        //Print the variable for each module code
        System.out.println("Variance for: " + moduleCode + " || " + variance(grade));
        System.out.println("----------------------------------------------------------------------");
    }

    public void standardVariance(String studentNumber) {
        //Calculates the standard deviation of a list of grades
        //Check to see if the student number passed in matches any of the students in the list
        // <Double>grade = new ArrayList<>();
        double[] grade = new double[studentList.size()];
        AtomicInteger counter = new AtomicInteger();
        studentList.stream()
                .filter(student -> student.getStudentNumber().equalsIgnoreCase(studentNumber))
                //loops through each course and assigns a grade to every student based on their respective grade list
                .forEach(student -> student.courseList
                        .forEach(course -> {
                            grade[counter.get()] = course.getGrade();
//                            grade.set(counter.get(), course.getGrade());
                            counter.getAndIncrement();
                        }));
        System.out.println("The standard deviance for  student: " + studentNumber + " || " + standardDeviace(grade));
        System.out.println("----------------------------------------------------------------------");
    }

    public double variance(double[] values) {
        //Calculates the average of a list of numbers and then calculates the variance for each number in that list.
        double variance;
        double average = calculateAverge(values);

//        System.out.println("Average: " + average);

        for (int i = 0; i < values.length; i++) {
            if (values[i] != 0) {
                //Calculate what those values would be if the were multiplied by 2 (Square root)
                //If not equal to zero, then it will calculate how much greater or lesser
                // than the average that number is by using Math.pow
                values[i] = Math.pow(values[i] - average, 2);
            }
        }
        variance = calculateAverge(values);
        //Returns the percentage of hoe much more or less than average each number is
        return Math.round(variance * 100.0) / 100.0;
    }

    public double standardDeviace(double[] values) {
        /*Calculates the standard deviation of a set of values
        It calculates the square root of variance, which is calculated by taking the average and subtracting it from each value in the list
        This gives an array with values that are squared to get their variance
         */
        return Math.round(Math.sqrt(variance(values)) * 100.0) / 100.0;
    }
    public double calculateAverge(double[] values) {
        //Calculate the average of a given array of values
        AtomicReference<Double> average = new AtomicReference<>(0.0);
        AtomicInteger total = new AtomicInteger();
        //For each value, update the reference with that value and increment the total counter
        Arrays.stream(values).forEach(value -> {
            average.updateAndGet(v -> v + value);
            total.getAndIncrement();

        });
        //Calculates how many times 100 is divided by this average using Math.round
        return Math.round(average.get() / total.get() * 100.0) / 100.0;
    }
}
//---CHECK---