package com.dublabs.Domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by tofiques on 11/30/17.
 */

@Table(name = "academic_records", schema = "csassignment", catalog = "")
public class AcademicRecordsEntity {
    private String studentGrade;
    private Integer courseYear;
    private Integer couesTerm;

    @Basic
    @Column(name = "student_grade")
    public String getStudentGrade() {
        return studentGrade;
    }

    public void setStudentGrade(String studentGrade) {
        this.studentGrade = studentGrade;
    }

    @Basic
    @Column(name = "course_year")
    public Integer getCourseYear() {
        return courseYear;
    }

    public void setCourseYear(Integer courseYear) {
        this.courseYear = courseYear;
    }

    @Basic
    @Column(name = "coues_term")
    public Integer getCouesTerm() {
        return couesTerm;
    }

    public void setCouesTerm(Integer couesTerm) {
        this.couesTerm = couesTerm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AcademicRecordsEntity that = (AcademicRecordsEntity) o;

        if (studentGrade != null ? !studentGrade.equals(that.studentGrade) : that.studentGrade != null) return false;
        if (courseYear != null ? !courseYear.equals(that.courseYear) : that.courseYear != null) return false;
        if (couesTerm != null ? !couesTerm.equals(that.couesTerm) : that.couesTerm != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = studentGrade != null ? studentGrade.hashCode() : 0;
        result = 31 * result + (courseYear != null ? courseYear.hashCode() : 0);
        result = 31 * result + (couesTerm != null ? couesTerm.hashCode() : 0);
        return result;
    }
}
