package com.cx.wz.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@MapperScan(value = "com.cx.wz", annotationClass = Mapper.class)
@EnableTransactionManagement
@PropertySources({
        @PropertySource(value = "classpath:sulucenter/database.properties"),
        @PropertySource(value = "file://${CONFIG_HOME}/sulucenter/database.properties", ignoreResourceNotFound = true)
})
public class RepoConfig {
    @Resource
    private Environment env;

    @Bean(destroyMethod = "close")
    public HikariDataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(env.getProperty("db.driver.classname"));
        hikariConfig.setJdbcUrl(env.getProperty("db.url"));
        hikariConfig.setUsername(env.getProperty("db.username"));
        hikariConfig.setPassword(env.getProperty("db.password"));
        hikariConfig.setMaximumPoolSize(env.getProperty("db.max.pool.size", Integer.class));
        return new HikariDataSource(hikariConfig);
    }


    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    @Bean
    public MybatisSqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);

        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setProperties(new Properties() {{
            setProperty("dialectType", "POSTGRE_SQL");
            setProperty("dialectClazz", "com.baomidou.mybatisplus.extension.plugins.pagination.dialects.PostgreDialect");
        }});
        sqlSessionFactory.setPlugins(paginationInterceptor);
        return sqlSessionFactory;
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();
        configuration.setCallSettersOnNulls(true);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setLazyLoadingEnabled(true);
        configuration.setAggressiveLazyLoading(false);
        configuration.getLazyLoadTriggerMethods().clear();
        return new SqlSessionTemplate(sqlSessionFactory, ExecutorType.REUSE);
    }

}
