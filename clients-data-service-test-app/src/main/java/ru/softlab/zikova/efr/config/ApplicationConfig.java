package ru.softlab.zikova.efr.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.softlab.efr.clients.support.authorization.EnableClientDataAuthorization;
import ru.softlab.efr.common.bpm.support.EnableBPM;
import ru.softlab.efr.common.settings.services.SettingsService;
import ru.softlab.efr.common.utilities.hibernate.annotations.EnableHibernateJpa;
import ru.softlab.efr.infrastructure.logging.annotations.EnableLogging;
import ru.softlab.efr.infrastructure.transport.annotations.EnableTransport;
import ru.softlab.efr.services.authorization.annotations.EnablePermissionControl;
import ru.softlab.efr.settings.logging.annotations.EnableSettingsClientLoggingConfiguration;

import static ru.softlab.zikova.efr.config.ApplicationConfig.*;

/**
 * Конф. класс приложения
 *
 * @author khudyakov
 * @since 28.07.2017
 */
@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
@ComponentScan(basePackages = "ru.softlab.zikova.efr")
@EnableHibernateJpa(
        repositoryPackages = "ru.softlab.zikova.efr.repository",
        modelPackages = "ru.softlab.zikova.efr.entity")
@EnableTransport(APPLICATION_NAME)
@EnableLogging
@EnableCaching
@EnableSettingsClientLoggingConfiguration
@EnablePermissionControl
@EnableClientDataAuthorization
@EnableBPM(serviceName = APPLICATION_NAME, basePath = APPLICATION_PATH)
public class ApplicationConfig {
    public static final String APPLICATION_NAME = "clients-data-service-test-app";
    public static final String PATH_DELIMITER = "/";
    public static final String APPLICATION_PATH = PATH_DELIMITER + APPLICATION_NAME;

    @Bean
    @Primary
    public CacheManager cacheManager() {
        return new SimpleCacheManager();
    }
}