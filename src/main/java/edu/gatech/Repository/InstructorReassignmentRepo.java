package edu.gatech.Repository;

import edu.gatech.Domain.InstructorReassignmentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by tofiques on 12/4/17.
 */

@Repository
public interface InstructorReassignmentRepo extends CrudRepository<InstructorReassignmentEntity,Long> {

    @Query("select p from InstructorReassignmentEntity p where  p.term=:term")
    InstructorReassignmentEntity findByTerm(@Param("term")Integer term);
}
