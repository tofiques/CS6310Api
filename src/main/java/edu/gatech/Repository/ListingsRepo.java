package edu.gatech.Repository;

import edu.gatech.Domain.ListingsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tofiques on 11/30/17.
 */
@Repository
public interface ListingsRepo extends CrudRepository<ListingsEntity,Long> {
}
