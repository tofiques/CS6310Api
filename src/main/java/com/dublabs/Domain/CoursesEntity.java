package com.dublabs.Domain;

import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.persistence.*;
import java.util.List;

/**
 * Created by tofiques on 11/30/17.
 */
@Entity
@Table(name = "courses", schema = "csassignment")
public class CoursesEntity {
    private int courseId;
    private String courseTitle;
    private Double courseCost;

    private List<Integer> prereq;

    private  InstructorsEntity instructor;

    /**
     * @return the instructor
     */
    @Transient
    public InstructorsEntity getInstructor() {
        return instructor;
    }

    /**
     * @param instructor the instructor to set
     */
    public void setInstructor(InstructorsEntity instructor) {
        this.instructor = instructor;
    }
    public CoursesEntity() {
    }

    public CoursesEntity(int courseId, String courseTitle, Double courseCost) {
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.courseCost = courseCost;
    }

    @Id
    @Column(name = "course_id")
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Basic
    @Column(name = "course_title")
    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    @Basic
    @Column(name = "course_cost")
    public Double getCourseCost() {
        return courseCost;
    }

    public void setCourseCost(Double courseCost) {
        this.courseCost = courseCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoursesEntity that = (CoursesEntity) o;

        if (courseId != that.courseId) return false;
        if (courseTitle != null ? !courseTitle.equals(that.courseTitle) : that.courseTitle != null) return false;
        if (courseCost != null ? !courseCost.equals(that.courseCost) : that.courseCost != null) return false;

        return true;
    }
    @Transient
    public List<Integer> getPrereq() {
        return prereq;
    }

    public void setPrereq(List<Integer> prereq) {
        this.prereq = prereq;
    }

    @Override
    public int hashCode() {
        int result = courseId;
        result = 31 * result + (courseTitle != null ? courseTitle.hashCode() : 0);
        result = 31 * result + (courseCost != null ? courseCost.hashCode() : 0);
        return result;
    }
}
