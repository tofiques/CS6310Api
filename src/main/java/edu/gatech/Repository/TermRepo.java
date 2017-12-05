package edu.gatech.Repository;


import edu.gatech.Domain.Term;
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

	@Query("SELECT  max(p.termId) from Term p")
	Term getMaxTerm();
	
}

