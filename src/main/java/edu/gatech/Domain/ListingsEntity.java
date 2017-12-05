package edu.gatech.Domain;

import javax.persistence.*;

/**
 * Created by tofiques on 12/1/17.
 */
@Entity
@Table(name = "listings", schema = "csassignment", catalog = "")
public class ListingsEntity {
    private Integer programId;
    private Integer ourseId;
    private int id;

    public ListingsEntity() {
    }

    public ListingsEntity(Integer programId, Integer ourseId) {
        this.programId = programId;
        this.ourseId = ourseId;
    }

    @Basic
    @Column(name = "program_id")
    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    @Basic
    @Column(name = "ourse_id")
    public Integer getOurseId() {
        return ourseId;
    }

    public void setOurseId(Integer ourseId) {
        this.ourseId = ourseId;
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

        ListingsEntity that = (ListingsEntity) o;

        if (id != that.id) return false;
        if (programId != null ? !programId.equals(that.programId) : that.programId != null) return false;
        if (ourseId != null ? !ourseId.equals(that.ourseId) : that.ourseId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = programId != null ? programId.hashCode() : 0;
        result = 31 * result + (ourseId != null ? ourseId.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
