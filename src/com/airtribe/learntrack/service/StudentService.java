package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;

import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private final List<Student> studentList = new ArrayList<>();

    public Student addStudent(String firstName, String lastName, String email, String batch) throws InvalidInputException {
        if (firstName == null || firstName.isBlank()) {
            throw new InvalidInputException("First name cannot be empty.");
        }
        if (lastName == null || lastName.isBlank()) {
            throw new InvalidInputException("Last name cannot be empty.");
        }
        if (!validEmail(email)) {
            throw new InvalidInputException("Invalid email format. Email must contain '@'. Received: " + email);
        }
        if (batch == null || batch.isBlank()) {
            throw new InvalidInputException("Batch cannot be empty.");
        }

        Student student = new Student(firstName, lastName, email, batch, true);
        studentList.add(student);
        return student;
    }

    public List<Student> getStudents(){
        if(studentList.isEmpty()){
            System.out.println("No Students Added");
        }
        return studentList;
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
            if(student.getId().equals(id)){
                student.setActive(false);
                return student;
            }
        }
        throw new EntityNotFoundException("Student with ID '" + id + "' was not found.");

    }
        public boolean validEmail(String email){
        return email.contains("@");
    }
}
