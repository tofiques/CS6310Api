package com.dublabs.Repository;

import com.dublabs.Domain.RequestsEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by tofiques on 11/30/17.
 */
public interface RequestRepo extends CrudRepository<RequestsEntity,Long> {
}


