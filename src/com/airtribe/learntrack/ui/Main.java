package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.entity.*;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;

import java.util.Scanner;

public class Main {

    public static final Scanner scanner = new Scanner(System.in);

    private static final StudentService studentService = new StudentService();
    private static final CourseService courseService = new CourseService();
    private static final EnrollmentService enrollmentService = new EnrollmentService(studentService,courseService);

    private static final String DOUBLE_LINE = "==================================";

    public static void main(String[] args) throws InvalidInputException {

        boolean running = true;
        while (running) {
            getMainMenu();
            int option = readInput("Enter option : ");
            switch (option) {
                case 0 -> {
                    System.out.println("Exiting Learn Track");
                    running = false;
                }
                case 1 -> getStudentMenu();
                case 2 -> getCourseMenu();
                case 3 -> getEnrollmentMenu();
                default -> System.out.println("Please choose option between 0 & 3");
            }
        }
    }

    public static void getMainMenu() {
        System.out.println(DOUBLE_LINE);
        System.out.println("Learn Track");
        System.out.println(DOUBLE_LINE);

        System.out.println("1. Student management");
        System.out.println("2. Course management");
        System.out.println("3. Enrollment management");
        System.out.println("0. Exit");
        System.out.println(DOUBLE_LINE);
    }

    // Student menu and operations
    private static void getStudentMenu() {
        boolean running = true;
        while (running) {
            System.out.println(DOUBLE_LINE);
            System.out.println("Student management");
            System.out.println(DOUBLE_LINE);

            System.out.println("1. Add new student");
            System.out.println("2. View all students");
            System.out.println("3. Search student by ID");
            System.out.println("4. Deactivate student");
            System.out.println("0. Back");

            System.out.println(DOUBLE_LINE);
            int option = readInput("Enter option: ");

            switch (option) {
                case 0 ->{
                    System.out.println("Exiting student management");
                    running = false;
                }
                case 1 -> studentService.addStudent();
                case 2 -> studentService.getStudents();
                case 3 -> studentService.searchStudentById();
                case 4 -> studentService.deactivateStudent();
                default -> System.out.println("Please enter options between 0 & 4");
            }
        }
    }
    // Course menu and operations
    private static void getCourseMenu() {

        boolean running = true;

        while(running){
            System.out.println(DOUBLE_LINE);
            System.out.println("Course management");
            System.out.println(DOUBLE_LINE);

            System.out.println("1. Add new course");
            System.out.println("2. View all courses");
            System.out.println("3. Activate course");
            System.out.println("4. Deactivate course");
            System.out.println("0. Back");
            System.out.println(DOUBLE_LINE);

            int option = readInput("Enter option: ");
            switch(option){
                case 0 -> {
                    System.out.println("Exiting course management");
                    running = false;
                }
                case 1 -> courseService.addNewCourse();
                case 2 -> courseService.getCourses();
                case 3 -> courseService.activateDeactivateCourse(true);
                case 4 -> courseService.activateDeactivateCourse(false);
                default -> System.out.println("Please enter option between 0 & 4");
            }
        }
    }


    private static void getEnrollmentMenu() {
       boolean running = true;

       while(running){
           System.out.println(DOUBLE_LINE);
           System.out.println("Enrollment Management");
           System.out.println(DOUBLE_LINE);

           System.out.println("1. Enroll a student in a course");
           System.out.println("2. View enrollment for a student");
           System.out.println("3. Mark enrollment as completed");
           System.out.println("4. Mark enrollment as cancelled");
           System.out.println("0. Back");
           System.out.println(DOUBLE_LINE);
           int option = readInput("Enter option: ");

           switch(option){
               case 0 -> {
                   System.out.println("Exiting enrollment management");
                   running = false;
               }
               case 1 -> {
                   Enrollment enrollment = enrollmentService.enrollStudent();
                   if(enrollment != null)
                   {
                       System.out.println("Enrollment created with ID "+enrollment.getId());
                   }
               }
               case 2 -> enrollmentService.viewEnrollment();
               case 3 -> enrollmentService.changeStatus(Status.COMPLETED);
               case 4 -> enrollmentService.changeStatus(Status.CANCELLED);
               default -> System.out.println("Please enter option between 0 & 4");
           }
       }
    }

    private static int readInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid numerical option.");
            }
        }
    }
}