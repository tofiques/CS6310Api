package edu.gatech.Repository;

import edu.gatech.Domain.PrereqsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tofiques on 11/30/17.
 */
@Repository
public interface PreReqRepo extends CrudRepository<PrereqsEntity,Long> {

    @Query("SELECT  p from PrereqsEntity p where p.courseId= :courseId")
    List<PrereqsEntity> findByCourseId( @Param("courseId") Integer courseId);


    @Query("SELECT  p.prereqCourseId from PrereqsEntity p where p.courseId= :courseId")
    List<Integer> findByCourseIdforPreRec( @Param("courseId") Integer courseId);
}
