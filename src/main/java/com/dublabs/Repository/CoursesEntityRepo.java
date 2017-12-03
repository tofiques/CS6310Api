package com.dublabs.Repository;

import com.dublabs.Domain.CoursesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tofiques on 11/30/17.
 */
@Repository
public interface CoursesEntityRepo extends CrudRepository<CoursesEntity,Long> {
	
}
