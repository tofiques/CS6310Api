package edu.gatech.Domain;

import javax.persistence.*;

/**
 * Created by tofiques on 12/1/17.
 */
@Entity
@Table(name = "programs", schema = "csassignment", catalog = "")
public class ProgramsEntity {
    private int programId;
    private String programTitle;

    public ProgramsEntity() {
    }

    public ProgramsEntity(int programId, String programTitle) {
        this.programId = programId;
        this.programTitle = programTitle;
    }

    @Id
    @Column(name = "program_id")
    public int getProgramId() {
        return programId;
    }

    public void setProgramId(int programId) {
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

        if (programId != that.programId) return false;
        if (programTitle != null ? !programTitle.equals(that.programTitle) : that.programTitle != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = programId;
        result = 31 * result + (programTitle != null ? programTitle.hashCode() : 0);
        return result;
    }
}
