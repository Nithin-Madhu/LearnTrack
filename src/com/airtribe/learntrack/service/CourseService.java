package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;

import java.util.ArrayList;
import java.util.List;

public class CourseService {

    private final List<Course> courseList = new ArrayList<>();

    public Course addCourse(String courseName, String description, int durationInWeeks) throws InvalidInputException {
        if (courseName == null || courseName.isBlank()) {
            throw new InvalidInputException("course name cannot be empty.");
        }
        if (description == null || description.isBlank()) {
            throw new InvalidInputException("description cannot be empty.");
        }

        if (durationInWeeks == 0) {
            throw new InvalidInputException("duration in weeks cannot be 0.");
        }

        Course course = new Course(courseName, description, durationInWeeks, true);
        courseList.add(course);
        return course;
    }

    public List<Course> getCourses() {
        if(courseList.isEmpty()){
            System.out.println("No Course Added");
        }
        return courseList;
    }

    public Course activateDeactivateCourse(String id, boolean flag) throws EntityNotFoundException {

        if (id == null || id.isBlank()) {
            throw new EntityNotFoundException("Course ID cannot be empty.");
        }

        for(Course course:courseList){
            if(course.getId().equals(id)){
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