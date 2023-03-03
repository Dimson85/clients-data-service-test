package ru.softlab.zikova.efr.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.softlab.efr.common.utilities.hibernate.annotations.EnableHibernateJpa;
import ru.softlab.efr.common.utilities.rest.annotations.EnableRestDataConverters;
import ru.softlab.efr.test.infrastructure.transport.rest.MockRestRule;
import ru.softlab.efr.test.infrastructure.transport.rest.config.AbstractTestConfiguration;
import ru.softlab.efr.test.infrastructure.transport.rest.transports.HttpTransport;
import ru.softlab.efr.test.services.auth.rest.config.AuthTestConfiguration;
import ru.softlab.zikova.efr.repository.ClientRepository;
import ru.softlab.zikova.efr.repository.ProductRepository;
import ru.softlab.zikova.efr.service.mapper.ClientMapper;


import static ru.softlab.zikova.efr.config.ApplicationConfig.APPLICATION_NAME;

/**
 * Конфиг для интеграционных тестов
 *
 * @author zykova
 * @since 04.05.2021
 */
@Configuration
@EnableWebMvc
@EnableRestDataConverters
@Import({HibernateConfig.class, AuthTestConfiguration.class})
@EnableHibernateJpa(
        repositoryPackages = {"ru.softlab.zikova.efr.repository"},
        modelPackages = {"ru.softlab.zikova.efr.entity"}
)
@ComponentScan({
        "ru.softlab.zikova.efr.repository",
        "ru.softlab.zikova.efr.service"
})
public class IntegrationTestApplicationConfig extends AbstractTestConfiguration {
    @Override
    protected String getTestAppContextRoot() {
        return APPLICATION_NAME;
    }

    @Override
    protected String getTestAppName() {
        return APPLICATION_NAME;
    }

    @Bean
    public MockRestRule mockRest(HttpTransport testHttpTransport) {
        return new MockRestRule(testHttpTransport);
    }

    @Bean
    public ClientRepository clientRepository() {
        return Mockito.mock(ClientRepository.class);
    }

    @Bean
    public ProductRepository productRepository() {
        return Mockito.mock(ProductRepository.class);
    }


}
