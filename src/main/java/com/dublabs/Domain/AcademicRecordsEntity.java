package com.dublabs.Domain;

import javax.persistence.*;

/**
 * Created by tofiques on 12/1/17.
 */
@Entity
@Table(name = "academic_records", schema = "csassignment")
public class AcademicRecordsEntity {
    private String studentGrade;
    private Integer courseYear;
    private Integer couesTerm;
    private int id;
    private Integer studentId;
	private Integer courseId;
	
	 /**
		 * @return the studentId
		 */
	    @Basic
	    @Column(name = "student_id")
		public Integer getStudentId() {
			return studentId;
		}

		/**
		 * @param studentId the studentId to set
		 */
		public void setStudentId(Integer studentId) {
			this.studentId = studentId;
		}

		/**
		 * @return the courseId
		 */
		@Basic
	    @Column(name = "course_id")
		public Integer getCourseId() {
			return courseId;
		}

		/**
		 * @param courseId the courseId to set
		 */
		public void setCourseId(Integer courseId) {
			this.courseId = courseId;
		}


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

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
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

        AcademicRecordsEntity that = (AcademicRecordsEntity) o;

        if (id != that.id) return false;
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
        result = 31 * result + id;
        return result;
    }
}
