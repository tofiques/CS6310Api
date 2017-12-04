package com.dublabs.Repository;

import com.dublabs.Domain.CoursesEntity;
import com.dublabs.Domain.InstructorsEntity;
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
