package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.util.IdGenerator;

import java.util.Date;
import java.util.Objects;

public class Enrollment {

    private static final IdGenerator ID_GENERATOR = new IdGenerator("ENR");


    private final String id;
    private String studentId;
    private String courseId;
    private Date enrollmentDate;
    private Status status;

    public Enrollment( String studentId, String courseId, Date enrollmentDate, Status status) {
        this.id = ID_GENERATOR.nextId();
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment that = (Enrollment) o;
        return Objects.equals(id, that.id) && Objects.equals(studentId, that.studentId) && Objects.equals(courseId, that.courseId) && Objects.equals(enrollmentDate, that.enrollmentDate) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studentId, courseId, enrollmentDate, status);
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "id='" + id + '\'' +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                ", enrollmentDate=" + enrollmentDate +
                ", status=" + status +
                '}';
    }
}
