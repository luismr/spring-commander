package dev.luismachadoreis.blueprint.cqs;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * SpringCommanderAutoConfiguration is an auto-configuration class for Spring Commander.
 * It is used to automatically configure the Spring Commander mediator.
 */
@AutoConfiguration
public class SpringCommanderAutoConfiguration {

    /**
     * Creates a new SpringCommanderMediator bean.
     * @param applicationContext The application context.
     * @return A new SpringCommanderMediator.
     */
    @Bean
    @ConditionalOnMissingBean
    public SpringCommanderMediator springMediator(ApplicationContext applicationContext) {
        return new SpringCommanderMediator(applicationContext);
    }

} 