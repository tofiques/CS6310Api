package com.dublabs.Domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by tofiques on 12/3/17.
 */
@Entity
@Table(name = "term", schema = "csassignment")
public class Term {
     private  Integer termId;

    public Term(Integer termId) {
        this.termId = termId;
    }

    public Term() {
		
	}

	@Id
    @Column(name = "term_id")
    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }
}
