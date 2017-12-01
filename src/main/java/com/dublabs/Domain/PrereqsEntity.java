package com.dublabs.Domain;

import javax.annotation.Generated;
import javax.persistence.*;

/**
 * Created by tofiques on 12/1/17.
 */
@Entity
@Table(name = "prereqs", schema = "csassignment", catalog = "")
public class PrereqsEntity {
    private Integer courseId;
    private Integer prereqCourseId;
    private int id;

    public PrereqsEntity() {
    }

    public PrereqsEntity(Integer courseId, Integer prereqCourseId) {
        this.courseId = courseId;
        this.prereqCourseId = prereqCourseId;
    }

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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrereqsEntity that = (PrereqsEntity) o;

        if (id != that.id) return false;
        if (courseId != null ? !courseId.equals(that.courseId) : that.courseId != null) return false;
        if (prereqCourseId != null ? !prereqCourseId.equals(that.prereqCourseId) : that.prereqCourseId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = courseId != null ? courseId.hashCode() : 0;
        result = 31 * result + (prereqCourseId != null ? prereqCourseId.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
