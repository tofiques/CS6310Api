package com.dublabs.Repository;

import com.dublabs.Domain.InstructorsEntity;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tofiques on 11/30/17.
 */
@Repository
public interface InstructorsRepo extends CrudRepository<InstructorsEntity,Integer> {

	@Query("SELECT   p from InstructorsEntity p where p.course_id = :courseId")
	List<InstructorsEntity> findByCourse_id(Integer courseId); 
	@Query("SELECT   p from InstructorsEntity p where p.instructor_id = :InstructorId")
	List<InstructorsEntity> findByInstructor_id(Integer instructorId);
}
