package com.dublabs.Repository;

import com.dublabs.Domain.StudentsEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by tofiques on 11/30/17.
 */
public interface StudentsRepo extends CrudRepository <StudentsEntity,Long> {
}
