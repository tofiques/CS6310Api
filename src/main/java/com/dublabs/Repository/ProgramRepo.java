package com.dublabs.Repository;

import com.dublabs.Domain.ProgramsEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by tofiques on 11/30/17.
 */
public interface ProgramRepo  extends CrudRepository<ProgramsEntity,Long>{
}
