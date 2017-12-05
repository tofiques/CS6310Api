package edu.gatech.Domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "instructors", schema = "csassignment")
public class InstructorsEntity {

    @Id
    @Column(name = "instructor_id")
    private Integer instructorId;
    @Column(name = "instr_name")
    private String name;
    @Column(name = "office_hours")
    private String availability;

    @Column(name = "email")
    private String email;

    @Column(name = "course_id")
    private Integer courseId;

    public InstructorsEntity(Integer instructorId, String name, String availability, String email, Integer courseId) {
        this.instructorId = instructorId;
        this.name = name;
        this.availability = availability;
        this.email = email;
        this.courseId = courseId;
    }

    public InstructorsEntity() {
    }


    public Integer getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Integer instructor_id) {
        this.instructorId = instructor_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String instr_name) {
        this.name = instr_name;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String office_hours) {
        this.availability = office_hours;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer course_id) {
        this.courseId = course_id;
    }
}
