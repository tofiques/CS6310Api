package com.dublabs.Domain;

import javax.persistence.*;

/**
 * Created by tofiques on 11/30/17.
 */
@Entity
@Table(name = "programs", schema = "csassignment", catalog = "")
public class ProgramsEntity {
    private Integer programId;
    private String programTitle;

    @Basic
    @Id
    @Column(name = "program_id")
    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    @Basic
    @Column(name = "program_title")
    public String getProgramTitle() {
        return programTitle;
    }

    public void setProgramTitle(String programTitle) {
        this.programTitle = programTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProgramsEntity that = (ProgramsEntity) o;

        if (programId != null ? !programId.equals(that.programId) : that.programId != null) return false;
        if (programTitle != null ? !programTitle.equals(that.programTitle) : that.programTitle != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = programId != null ? programId.hashCode() : 0;
        result = 31 * result + (programTitle != null ? programTitle.hashCode() : 0);
        return result;
    }
}
