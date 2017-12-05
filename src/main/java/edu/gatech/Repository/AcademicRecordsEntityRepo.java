package edu.gatech.Repository;

import edu.gatech.Domain.AcademicRecordsEntity;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by tofiques on 11/30/17.
 */

@Repository
public  interface AcademicRecordsEntityRepo extends CrudRepository<AcademicRecordsEntity,Long> {

	@Query("SELECT   p from AcademicRecordsEntity p where p.studentId = :studentId")
	List<AcademicRecordsEntity> findByStudentId(@Param("studentId")Integer studentId);

}
