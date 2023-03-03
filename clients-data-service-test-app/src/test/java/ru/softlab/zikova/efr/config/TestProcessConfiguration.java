package ru.softlab.zikova.efr.config;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.softlab.efr.common.bpm.support.EnableBPM;
import ru.softlab.efr.test.common.bpm.BpmProcessTestConfig;
import ru.softlab.zikova.efr.processes.SimpleUserTask;

import static ru.softlab.zikova.efr.config.ApplicationConfig.APPLICATION_NAME;
import static ru.softlab.zikova.efr.config.ApplicationConfig.APPLICATION_PATH;

/**
 * @author niculichev
 * @since 23.05.2018
 */
@Configuration
@ComponentScan(basePackages = "ru.softlab.zikova.efr")
@EnableWebMvc
@EnableBPM(serviceName = APPLICATION_NAME, basePath = APPLICATION_PATH)
public class TestProcessConfiguration extends BpmProcessTestConfig {
    @Bean
    public SimpleUserTask simpleUserTask() {
        return Mockito.mock(SimpleUserTask.class);
    }

}