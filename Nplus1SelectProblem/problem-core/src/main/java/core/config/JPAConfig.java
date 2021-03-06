package core.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.SessionFactory;
import org.postgresql.Driver;
import org.postgresql.ds.PGPoolingDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateExceptionTranslator;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Created by radu.
 */

@Configuration
@EnableJpaRepositories({"core.repository"})
@EnableTransactionManagement
@EnableCaching
@ComponentScan("core.service")
//public class JPAConfig {
//
//    @Value("${db.jdbcUrl}")
//    //@Value("jdbc:postgresql://localhost:5432/postgres")
//    private String jdbcUrl;
//
//    @Value("${db.username}")
//    //@Value("postgres")
//    private String username;
//
//    @Value("${db.password}")
//    //@Value("terno")
//    private String password;
//
//    @Value("true")
//    private Boolean generateDDL;
//
//    /**
//     * http://www.baeldung.com/hikaricp
//     *
//     * @return
//     */
//    @Bean
//    public DataSource dataSource() {
//        HikariConfig config = new HikariConfig();
//        config.setJdbcUrl(jdbcUrl);
//        config.setUsername(username);
//        config.setPassword(password);
//        config.addDataSourceProperty("cachePrepStmts", "true");
//        config.addDataSourceProperty("prepStmtCacheSize", "250");
//        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
//        config.setDriverClassName(Driver.class.getName());
//        HikariDataSource dataSource = new HikariDataSource(config);
////        PGPoolingDataSource dataSource = new PGPoolingDataSource();
////        //            dataSource.setUrl(jdbcURL);
////        dataSource.setServerName("localhost");
////        dataSource.setDatabaseName("courses");
////        dataSource.setUser("postgres");
////        dataSource.setPassword("terno");
////        dataSource.setMaxConnections(4);
//        return dataSource;
//    }
//
//    @Bean
//    public EntityManagerFactory entityManagerFactory() {
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        vendorAdapter.setDatabase(Database.POSTGRESQL);
//        vendorAdapter.setGenerateDdl(generateDDL);
//        vendorAdapter.setShowSql(true);
//
//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//        factory.setJpaVendorAdapter(vendorAdapter);
//        factory.setPackagesToScan("core.domain");
//        factory.setDataSource(dataSource());
//        factory.afterPropertiesSet();
//        return factory.getObject();
//    }
//
//    @Bean
//    public EntityManager entityManager() {
//        return entityManagerFactory().createEntityManager();
//    }
//
//    @Bean
//    PlatformTransactionManager transactionManager() {
//        JpaTransactionManager manager = new JpaTransactionManager();
//        manager.setEntityManagerFactory(entityManagerFactory());
//        return manager;
//    }
//
//    @Bean
//    public HibernateExceptionTranslator hibernateExceptionTranslator() {
//        return new HibernateExceptionTranslator();
//    }
//
//
//}
public class JPAConfig {

    @Bean
    public DataSource dataSource() {
        PGPoolingDataSource dataSource = new PGPoolingDataSource();
        //            dataSource.setUrl(jdbcURL);
        dataSource.setServerName("localhost");
        dataSource.setDatabaseName("postgres");
        dataSource.setUser("postgres");
        dataSource.setPassword("terno");
        dataSource.setMaxConnections(4);

        return dataSource;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.POSTGRESQL);
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("core.domain");
        factory.setDataSource(dataSource());
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    public EntityManager entityManager() {
        return entityManagerFactory().createEntityManager();
    }

    @Bean
    PlatformTransactionManager transactionManager() {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(entityManagerFactory());
        return manager;
    }

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }
}

