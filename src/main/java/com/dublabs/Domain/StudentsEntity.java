package com.dublabs.Domain;

import javax.persistence.*;

/**
 * Created by tofiques on 11/30/17.
 */
@Entity
@Table(name = "students", schema = "csassignment")
public class StudentsEntity {
    private int studentId;
    private String studentName;
    private String studentAddr;
    private String studentPhone;
    private Integer programId;

    public StudentsEntity(int studentId, String studentName, String studentAddr, String studentPhone, Integer programId) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentAddr = studentAddr;
        this.studentPhone = studentPhone;
        this.programId = programId;
    }

    public StudentsEntity() {
    }

    @Id
    @Column(name = "student_id")
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Basic
    @Column(name = "student_name")
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Basic
    @Column(name = "student_addr")
    public String getStudentAddr() {
        return studentAddr;
    }

    public void setStudentAddr(String studentAddr) {
        this.studentAddr = studentAddr;
    }

    @Basic
    @Column(name = "student_phone")
    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    @Basic
    @Column(name = "program_id")
    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentsEntity that = (StudentsEntity) o;

        if (studentId != that.studentId) return false;
        if (studentName != null ? !studentName.equals(that.studentName) : that.studentName != null) return false;
        if (studentAddr != null ? !studentAddr.equals(that.studentAddr) : that.studentAddr != null) return false;
        if (studentPhone != null ? !studentPhone.equals(that.studentPhone) : that.studentPhone != null) return false;
        if (programId != null ? !programId.equals(that.programId) : that.programId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = studentId;
        result = 31 * result + (studentName != null ? studentName.hashCode() : 0);
        result = 31 * result + (studentAddr != null ? studentAddr.hashCode() : 0);
        result = 31 * result + (studentPhone != null ? studentPhone.hashCode() : 0);
        result = 31 * result + (programId != null ? programId.hashCode() : 0);
        return result;
    }
}
