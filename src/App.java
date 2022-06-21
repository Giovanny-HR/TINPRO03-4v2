/*
Giovanny Marchena
1021941
TI1A
 */

import java.util.*;

public class App {
    public static void main(String[] args) {
        Administrative administrative = new Administrative();
        /*
        //-----AVAILABLE COURSES--------
        "TINTES01-1"||"TINPRO03-1"||"TINPR04-1"||"TINDTB02-1"||"TINSEC04-1",

        //-----AVAILABLE Student Number--------
        "1021941"||"1647853"||"1458627"||"1643785"||"0965488"

        toBeCompletedCourses
        averageGradeCourse
        sumGradeCourse
        averageMaleFemale
        variance
        standardVariance
        notCompletedCourse
         */


        administrative.studentsWithAcquiredField("TINTES01-1");
        administrative.toBeCompletedCourses("1647853");
        administrative.variance("TINPRO03-1");
        administrative.averageMaleFemale("TINDTB02-1");
        administrative.averageGradeCourse("TINSEC04-1");
        administrative.sumGradeCourse("TINPR04-1");

    }



}
