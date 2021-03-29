package dev.brunocesar.bank.desktop.hml;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Desktop Adapter + System-core + MockAdapter
 *
 * @author Bruno César - https://github.com/brunocesarmp
 * @since 2021-03-29
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({
        "dev.brunocesar.bank.desktop.hml",
        "dev.brunocesar.bank.desktop.screen",
        "dev.brunocesar.bank.systemcore",
        "dev.brunocesar.bank.services.repository"
})
public class BuildDesktopSystemHsqldbConfig {

    @Bean
    public DataSource dataSource() {
        var builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.HSQL)
                .addScript("create-db.sql")
                .addScript("insert-hml.sql")
                .build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public DataSourceTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }


}
