package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;

import java.util.ArrayList;
import java.util.List;

import static com.airtribe.learntrack.ui.Main.scanner;

public class CourseService {

    private final List<Course> courseList = new ArrayList<>();

    public void activateDeactivateCourse(boolean flag) {
        try{
            System.out.print("Enter course ID: ");
            String id = scanner.nextLine().trim();
            Course course = activateDeactivateCourse(id,flag);
            if(course != null){
                System.out.println("Course with ID:" + id + " is updated : "+course);
            }
        }catch(EntityNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public void addNewCourse() {
        try {
            System.out.print("Please enter course name: ");
            String courseName = scanner.nextLine().trim();

            System.out.print("Please enter description: ");
            String description = scanner.nextLine().trim();

            System.out.print("Please enter duration in weeks: ");
            String input = scanner.nextLine().trim();

            int durationInWeeks;
            try {
                durationInWeeks = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                throw new InvalidInputException("Duration must be a whole number.");
            }

            Course course = addCourse(courseName, description, durationInWeeks);
            if(course != null){
                System.out.println("Course created with ID : " + course.getId());
            }

        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }

    public Course addCourse(String courseName, String description, int durationInWeeks) throws InvalidInputException {
        if (courseName == null || courseName.isBlank()) {
            throw new InvalidInputException("course name cannot be empty.");
        }
        if (description == null || description.isBlank()) {
            throw new InvalidInputException("description cannot be empty.");
        }

        if (durationInWeeks <= 0) {
            throw new InvalidInputException("duration in weeks cannot be 0 or less than 0.");
        }

        Course course = new Course(courseName, description, durationInWeeks, true);
        courseList.add(course);
        return course;
    }

    public void getCourses() {
        if(courseList.isEmpty()){
            System.out.println("No Course Added");
        }
        for(Course course:courseList){
            System.out.println(course);
        }
    }

    public Course activateDeactivateCourse(String id, boolean flag) throws EntityNotFoundException {

        if (id == null || id.isBlank()) {
            throw new EntityNotFoundException("Course ID cannot be empty.");
        }

        for(Course course:courseList){
            if(course.getId().equalsIgnoreCase(id)){
                course.setActive(flag);
                return course;
            }
        }
        throw new EntityNotFoundException("Course with ID '" + id + "' was not found.");

    }

    public Course getCourse(String id) throws EntityNotFoundException {
        if (id == null || id.isBlank()) {
            throw new EntityNotFoundException("Course ID cannot be empty.");
        }

        for (Course course : courseList) {
            if (course.getId().equalsIgnoreCase(id)) {
                return course;
            }
        }
        throw new EntityNotFoundException("Course with ID '" + id + "' was not found.");
    }
}