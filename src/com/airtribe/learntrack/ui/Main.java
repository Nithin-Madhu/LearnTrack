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
                case 0:
                    System.out.println("Exiting Learn Track");
                    running = false;
                    break;
                case 1:
                    getStudentMenu();
                    break;
                case 2:
                    getCourseMenu();
                    break;
                case 3:
                    getEnrollmentMenu();
                    break;
                default:
                    System.out.println("Please choose option between 0 & 3");
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
                case 0:
                    System.out.println("Exiting student management");
                    running = false;
                    break;
                case 1:
                    addStudent();
                    break;
                case 2:
                    getStudents();
                    break;
                case 3:
                    searchStudentById();
                    break;
                case 4:
                    deactivateStudent();
                    break;
                default:
                    System.out.println("Please enter options between 0 & 4");
            }
        }
    }

    public static void addStudent() {
        try {
            System.out.print("Please enter first name: ");
            String firstName = scanner.nextLine().trim();

            System.out.print("Please enter last name: ");
            String lastName = scanner.nextLine().trim();

            System.out.print("Please enter email: ");
            String email = scanner.nextLine().trim();

            System.out.print("Please enter batch: ");
            String batch = scanner.nextLine().trim();

            Student student = studentService.addStudent(firstName, lastName, email, batch);
            System.out.println("Student created with id : " + student.getId());

        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getStudents() {
        for (Student student : studentService.getStudents()) {
            System.out.println(student);
        }
    }

    public static void searchStudentById() {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine().trim();

        try {
            Student student = studentService.getStudent(id);
            System.out.println("Found student: " + student);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deactivateStudent() {
        try {
            System.out.print("Enter student ID: ");
            String id = scanner.nextLine().trim();
            Student student = studentService.deactivateStudent(id);
            System.out.println("Student with ID:" + id + " is deactivated : ");
            System.out.println(student);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
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
                case 1 -> addNewCourse();
                case 2 -> getCourses();
                case 3 -> activateDeactivateCourse(true);
                case 4 -> activateDeactivateCourse(false);
                default -> System.out.println("Please enter option between 0 & 4");
            }

        }

    }

    private static void activateDeactivateCourse(boolean flag) {
        try{
            System.out.print("Enter course ID: ");
            String id = scanner.nextLine().trim();
            Course course = courseService.activateDeactivateCourse(id,flag);
            System.out.println("Course with ID:" + id + " is updated : ");
            System.out.println(course);
        }catch(EntityNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    private static void addNewCourse() {
        try {
            System.out.print("Please enter course name: ");
            String courseName = scanner.nextLine().trim();

            System.out.print("Please enter description: ");
            String description = scanner.nextLine().trim();

            System.out.print("Please enter duration in weeks: ");
            int durationInWeeks = scanner.nextInt();

            Course course = courseService.addCourse(courseName, description, durationInWeeks);
            System.out.println("Course created with name : " + course.getCourseName());

        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getCourses() {
        for (Course course : courseService.getCourses()) {
            System.out.println(course);
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
                   System.out.println("Enrollment created with ID "+enrollment.getId());
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