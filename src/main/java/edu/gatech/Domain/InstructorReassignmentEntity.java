package edu.gatech.Domain;

import javax.persistence.*;

/**
 * Created by tofiques on 12/4/17.
 */
@Entity
@Table(name = "instructor_reassignment", schema = "csassignment", catalog = "")
public class InstructorReassignmentEntity {
    private int id;
    private Integer term;
    private String available;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "term")
    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    @Basic
    @Column(name = "available")
    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InstructorReassignmentEntity that = (InstructorReassignmentEntity) o;

        if (id != that.id) return false;
        if (term != null ? !term.equals(that.term) : that.term != null) return false;
        if (available != null ? !available.equals(that.available) : that.available != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (term != null ? term.hashCode() : 0);
        result = 31 * result + (available != null ? available.hashCode() : 0);
        return result;
    }
}
