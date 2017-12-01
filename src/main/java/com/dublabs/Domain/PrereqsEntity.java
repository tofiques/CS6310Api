package com.dublabs.Domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by tofiques on 11/30/17.
 */

@Table(name = "prereqs", schema = "csassignment", catalog = "")
public class PrereqsEntity {
    private Integer courseId;
    private Integer prereqCourseId;

    @Basic
    @Column(name = "course_id")
    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    @Basic
    @Column(name = "prereq_course_id")
    public Integer getPrereqCourseId() {
        return prereqCourseId;
    }

    public void setPrereqCourseId(Integer prereqCourseId) {
        this.prereqCourseId = prereqCourseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrereqsEntity that = (PrereqsEntity) o;

        if (courseId != null ? !courseId.equals(that.courseId) : that.courseId != null) return false;
        if (prereqCourseId != null ? !prereqCourseId.equals(that.prereqCourseId) : that.prereqCourseId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = courseId != null ? courseId.hashCode() : 0;
        result = 31 * result + (prereqCourseId != null ? prereqCourseId.hashCode() : 0);
        return result;
    }
}
