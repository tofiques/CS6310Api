package com.dublabs.Repository;

import com.dublabs.Domain.StudentsEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by tofiques on 11/30/17.
 */
@Repository
public interface StudentsRepo extends CrudRepository <StudentsEntity,Long> {

	@Query("SELECT   p from StudentsEntity p where p.studentId = :studentId")
	StudentsEntity findByStudentId(@Param("studentId")Integer studentId); 
}
