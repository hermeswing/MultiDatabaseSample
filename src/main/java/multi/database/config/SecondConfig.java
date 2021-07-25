package multi.database.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:/application.yml")
@MapperScan(value = "multi.database.mssql.dao", sqlSessionFactoryRef = "secondSqlSessionFactory")
public class SecondConfig {
    private static final Logger logger = LoggerFactory.getLogger(SecondConfig.class);

    @Autowired
    private ApplicationContext applicationContext;

    @Value("${spring.second.datasource.mapper-locations}")
    private String mapperLocations;

    @Value("${spring.second.datasource.mybatis-config}")
    private String configPath;

    @Value("${spring.second.datasource.driver-class-name}")
    private String dbDriverClassName;
    @Value("${spring.second.datasource.jdbc-url}")
    private String dbJdbcUrl;

    @Value("${spring.second.datasource.username}")
    private String dbUsername;

    @Value("${spring.second.datasource.password}")
    private String dbPassword;

    @Bean(name = "secondDataSource")
    //@Primary              Second 는 Primary 가 아님.
    @ConfigurationProperties(prefix = "spring.second.datasource")
    public DataSource secondDataSource() {
        logger.debug("[SecondConfig >> secondDataSource] dbDriverClassName :: {}", dbDriverClassName);
        logger.debug("[SecondConfig >> secondDataSource] dbJdbcUrl :: {}", dbJdbcUrl);
        logger.debug("[SecondConfig >> secondDataSource] dbUsername :: {}", dbUsername);
        logger.debug("[SecondConfig >> secondDataSource] dbPassword :: {}", dbPassword);

        DataSource dataSource = DataSourceBuilder.create().build();

//        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//        dataSourceBuilder.driverClassName(dbDriverClassName);
//        dataSourceBuilder.url(dbJdbcUrl);
//        dataSourceBuilder.username(dbUsername);
//        dataSourceBuilder.password(dbPassword);
//        DataSource dataSource = dataSourceBuilder.build();

        logger.info("Datasource : {}", dataSource);

        return dataSource;
    }

    @Bean(name = "secondSqlSessionFactory")
    //@Primary              Second 는 Primary 가 아님.
    public SqlSessionFactory secondSqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        logger.debug("[SecondConfig >> secondSqlSessionFactory] mapperLocations :: {}", mapperLocations);
        logger.debug("[SecondConfig >> secondSqlSessionFactory] getResources :: {}", applicationContext.getResources(mapperLocations));

        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources(mapperLocations));

//		Properties properties = new Properties();
//		properties.put("mapUnderscoreToCamelCase", true);
//		sqlSessionFactoryBean.setConfigurationProperties(properties);

        //Mybatis config파일 위치
        Resource myBatisConfig = new PathMatchingResourcePatternResolver().getResource(configPath);
        sqlSessionFactoryBean.setConfigLocation(myBatisConfig);

        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "secondSqlSessionTemplate")
    //@Primary              Second 는 Primary 가 아님.
    public SqlSessionTemplate secondSqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
