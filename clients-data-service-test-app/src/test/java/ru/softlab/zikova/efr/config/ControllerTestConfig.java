package ru.softlab.zikova.efr.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.softlab.efr.common.utilities.rest.annotations.EnableRestDataConverters;
import ru.softlab.zikova.efr.repository.ClientRepository;
import ru.softlab.zikova.efr.repository.ProductRepository;
import ru.softlab.zikova.efr.service.ClientService;
import ru.softlab.zikova.efr.service.ProductService;

/**
 * @author zykova
 * @since 28.04.2021
 */
@Configuration
@EnableRestDataConverters
@EnableSpringDataWebSupport
@EnableWebMvc
@ComponentScan(basePackages = "ru.softlab.zikova.efr.service")
public class ControllerTestConfig {

    @Bean
    public ClientService clientService() {
        return Mockito.mock(ClientService.class);
    }

    @Bean
    public ProductService productService() {
        return Mockito.mock(ProductService.class);
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
