package com.dublabs.Repository;


import com.dublabs.Domain.StudentsEntity;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tofiques on 11/30/17.
 */
@Repository
public interface StudentsRepo extends CrudRepository <StudentsEntity,Long> {
	
	@Query("SELECT   p from StudentsEntity p where p.studentId = :studentId")
	List<StudentsEntity> findByStudentId(Integer studentId); 
	//@Query("SELECT   p from InstructorsEntity p where p.instructor_id = :instructorId")
	//List<InstructorsEntity> findByInstructor_id(Integer instructorId);
}
