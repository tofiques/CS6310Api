package com.dublabs.Repository;


import com.dublabs.Domain.Term;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

/**
 * 
 * @author jineshk
 *
 */
@Repository
public interface TermRepo extends CrudRepository<Term,Integer> {

	@Query("SELECT  max(p.term_id) from Term p")
	Term getMaxTerm();
	
}

