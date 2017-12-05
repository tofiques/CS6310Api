
package edu.gatech.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;


/**
 * Created by tofiques on 2/11/17.
 */

@Configuration
@EnableAutoConfiguration


@EntityScan(basePackages = {"edu.gatech.Domain"})
@EnableJpaRepositories(basePackages = {"edu.gatech.Repository"})
@EnableTransactionManagement
public class RepositoryConfiguration {




}

