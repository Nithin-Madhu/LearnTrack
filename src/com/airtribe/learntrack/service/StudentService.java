package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.util.InputValidator;

import java.util.ArrayList;
import java.util.List;

import static com.airtribe.learntrack.ui.Main.scanner;

public class StudentService {

    private final List<Student> studentList = new ArrayList<>();

    private final InputValidator inputValidator = new InputValidator();

    public void addStudent() {
        try {
            System.out.print("Please enter first name: ");
            String firstName = scanner.nextLine().trim();

            System.out.print("Please enter last name: ");
            String lastName = scanner.nextLine().trim();

            System.out.print("Please enter email: ");
            String email = scanner.nextLine().trim();

            System.out.print("Please enter batch: ");
            String batch = scanner.nextLine().trim();

            Student student = addStudent(firstName, lastName, email, batch);
            if(student != null){
                System.out.println("Student created with id : " + student.getId());
            }

        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }

    public Student addStudent(String firstName, String lastName, String email, String batch) throws InvalidInputException {
        if (firstName == null || firstName.isBlank()) {
            throw new InvalidInputException("First name cannot be empty.");
        }
        if (lastName == null || lastName.isBlank()) {
            throw new InvalidInputException("Last name cannot be empty.");
        }
        if (!inputValidator.validEmail(email)) {
            throw new InvalidInputException("Invalid email format. Email must contain '@' & . Received: " + email);
        }
        if (batch == null || batch.isBlank()) {
            throw new InvalidInputException("Batch cannot be empty.");
        }

        Student student = new Student(firstName, lastName, email, batch, true);
        studentList.add(student);
        return student;
    }


    public void searchStudentById() {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine().trim();

        try {
            Student student = getStudent(id);
            if(student != null){
                System.out.println("Found student: " + student);
            }
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deactivateStudent() {
        try {
            System.out.print("Enter student ID: ");
            String id = scanner.nextLine().trim();
            Student student = deactivateStudent(id);
            System.out.println("Student with ID:" + id + " is deactivated : ");
            System.out.println(student);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    public void getStudents(){
        if(studentList.isEmpty()){
            System.out.println("No Students Added");
        }

        for(Student student:studentList){
            System.out.println(student);
        }
    }

    public Student getStudent(String id) throws EntityNotFoundException {
        if (id == null || id.isBlank()) {
            throw new EntityNotFoundException("Student ID cannot be empty.");
        }

        for (Student student : studentList) {
            if (student.getId().equalsIgnoreCase(id)) {
                return student;
            }
        }
        throw new EntityNotFoundException("Student with ID '" + id + "' was not found.");
    }

    public Student deactivateStudent(String id) throws EntityNotFoundException{

        if (id == null || id.isBlank()) {
            throw new EntityNotFoundException("Student ID cannot be empty.");
        }

        for(Student student:studentList){
            if(student.getId().equalsIgnoreCase(id)){
                student.setActive(false);
                return student;
            }
        }
        throw new EntityNotFoundException("Student with ID '" + id + "' was not found.");

    }

}
