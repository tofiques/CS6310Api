package com.dublabs.Domain;

import javax.persistence.*;

/**
 * Created by tofiques on 11/30/17.
 */
@Entity
@Table(name = "courses", schema = "csassignment")
public class CoursesEntity {
    private int courseId;
    private String courseTitle;
    private Double courseCost;

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

    @Override
    public int hashCode() {
        int result = courseId;
        result = 31 * result + (courseTitle != null ? courseTitle.hashCode() : 0);
        result = 31 * result + (courseCost != null ? courseCost.hashCode() : 0);
        return result;
    }
}
