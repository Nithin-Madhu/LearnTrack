package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Status;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.airtribe.learntrack.ui.Main.scanner;

public class EnrollmentService {

    private final List<Enrollment> enrollmentList = new ArrayList<>();
    private StudentService studentService = new StudentService();
    private CourseService courseService = new CourseService();


    public EnrollmentService(StudentService studentService ,CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }


    public Enrollment enrollStudent() {

        try{
            System.out.print("Please enter student ID: ");
            String studentId = scanner.nextLine().trim();
            Student student = studentService.getStudent(studentId);

            System.out.print("Please enter course ID: ");
            String courseId = scanner.nextLine().trim();
            Course course = courseService.getCourse(courseId);

            if(student != null && course != null){
                Enrollment enrollment = new Enrollment(studentId,courseId,new Date(),Status.ACTIVE);
                enrollmentList.add(enrollment);
                return enrollment;
            }

        }catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    public void viewEnrollment() {
        try{
            System.out.print("Please enter student ID: ");
            String studentId = scanner.nextLine().trim();

            for(Enrollment enrollment:enrollmentList){
                if(enrollment.getStudentId().equalsIgnoreCase(studentId)){
                    System.out.println(enrollment);
                }else{
                    throw new EntityNotFoundException();
                }
            }

        }catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    public void changeStatus(Status status) {

        try{
            System.out.print("Enter enrollment ID : ");
            String enrollmentId = scanner.nextLine().trim();

            for(Enrollment enrollment:enrollmentList){
                if(enrollment.getId().equalsIgnoreCase(enrollmentId)){
                    enrollment.setStatus(status);
                    System.out.println(enrollment);
                }else{
                    throw new EntityNotFoundException();
                }
            }
        }catch(EntityNotFoundException e){
            System.out.println(e.getMessage());
        }

    }
}
