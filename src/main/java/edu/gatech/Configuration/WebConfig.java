package edu.gatech.Configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by tofiques on 12/4/17.
 */
@Configuration
@ComponentScan(basePackages = { "edu.gatech" })
public class WebConfig extends WebMvcConfigurerAdapter {
}


