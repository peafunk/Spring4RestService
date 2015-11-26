package com.peafunk.spring4rest

import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.AnnotationConfigWebContextLoader
import org.springframework.test.context.web.WebAppConfiguration

import spock.lang.Specification

import com.peafunk.spring4rest.config.Spring4RestServiceConfig

@ContextConfiguration(classes = Spring4RestServiceConfig.class, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
@ActiveProfiles("unit-test")
public class AbstractTest extends Specification {

}
