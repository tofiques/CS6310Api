package edu.gatech.Repository;

import edu.gatech.Domain.ProgramsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tofiques on 11/30/17.
 */
@Repository
public interface ProgramRepo  extends CrudRepository<ProgramsEntity,Long>{
}
