package com.dublabs.Repository;

import com.dublabs.Domain.InstructorsEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by tofiques on 11/30/17.
 */
public interface InstructorsRepo extends CrudRepository<InstructorsEntity,Long> {


}
