package edu.gatech.Repository;

import edu.gatech.Domain.CoursesEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tofiques on 11/30/17.
 */
@Repository
public interface CoursesEntityRepo extends CrudRepository<CoursesEntity,Long> {


    @Query("SELECT   p from CoursesEntity p ")
    List<CoursesEntity> getAll();
    @Query("SELECT   p from CoursesEntity p where p.courseId = :courseId")
	CoursesEntity findByCourseId(@Param("courseId")Integer courseId); 
}
