package edu.gatech.Repository;

import edu.gatech.Domain.InstructorsEntity;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by tofiques on 11/30/17.
 */
@Repository
public interface InstructorsRepo extends CrudRepository<InstructorsEntity,Integer> {

	@Query("SELECT   p from InstructorsEntity p where p.courseId = :courseId")
	InstructorsEntity findByCourse_id(@Param("courseId")Integer courseId);
	@Query("SELECT   p from InstructorsEntity p where p.instructorId = :instructorId")
	InstructorsEntity findByInstructor_id(@Param("instructorId")Integer instructorId);

	@Query("SELECT   p from InstructorsEntity p ")
	List<InstructorsEntity> getAll();
}

