package com.dublabs.Domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "instructors", schema = "csassignment")
public class InstructorsEntity {

 @Id
 private Integer instructor_id;
  private String instr_name;
  private String office_hours;
  private String email;
  private Integer course_id;

  public InstructorsEntity(Integer instructor_id, String instr_name, String office_hours, String email, Integer course_id) {
    this.instructor_id = instructor_id;
    this.instr_name = instr_name;
    this.office_hours = office_hours;
    this.email = email;
    this.course_id = course_id;
  }

  public InstructorsEntity() {
  }

  public Integer getInstructor_id() {
    return instructor_id;
  }

  public void setInstructor_id(Integer instructor_id) {
    this.instructor_id = instructor_id;
  }

  public String getInstr_name() {
    return instr_name;
  }

  public void setInstr_name(String instr_name) {
    this.instr_name = instr_name;
  }

  public String getOffice_hours() {
    return office_hours;
  }

  public void setOffice_hours(String office_hours) {
    this.office_hours = office_hours;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Integer getCourse_id() {
    return course_id;
  }

  public void setCourse_id(Integer course_id) {
    this.course_id = course_id;
  }
}
