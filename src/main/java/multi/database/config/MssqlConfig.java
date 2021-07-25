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
@MapperScan(value = "multi.database.mssql.dao", sqlSessionFactoryRef = "mssqlSqlSessionFactory")
public class MssqlConfig {
    private static final Logger logger = LoggerFactory.getLogger(MssqlConfig.class);

    @Autowired
    private ApplicationContext applicationContext;

    @Value("${spring.mssql.datasource.mapper-locations}")
    private String mapperLocations;

    @Value("${spring.mssql.datasource.mybatis-config}")
    private String configPath;

    @Value("${spring.mssql.datasource.driver-class-name}")
    private String dbDriverClassName;
    @Value("${spring.mssql.datasource.jdbc-url}")
    private String dbJdbcUrl;

    @Value("${spring.mssql.datasource.username}")
    private String dbUsername;

    @Value("${spring.mssql.datasource.password}")
    private String dbPassword;

    @Bean(name = "mssqlDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.mssql.datasource")
    public DataSource mssqlDataSource() {
        logger.debug("[MssqlConfig >> mssqlDataSource] dbDriverClassName :: {}", dbDriverClassName);
        logger.debug("[MssqlConfig >> mssqlDataSource] dbJdbcUrl :: {}", dbJdbcUrl);
        logger.debug("[MssqlConfig >> mssqlDataSource] dbUsername :: {}", dbUsername);
        logger.debug("[MssqlConfig >> mssqlDataSource] dbPassword :: {}", dbPassword);

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

    @Bean(name = "mssqlSqlSessionFactory")
    @Primary
    public SqlSessionFactory mssqlSqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources(mapperLocations));

        logger.debug("[MssqlConfig >> mssqlSqlSessionFactory] mapperLocations :: {}", mapperLocations);
        logger.debug("[MssqlConfig >> mssqlSqlSessionFactory] getResources :: {}",
                                                     applicationContext.getResources(mapperLocations));

//		Properties properties = new Properties();
//		properties.put("mapUnderscoreToCamelCase", true);
//		sqlSessionFactoryBean.setConfigurationProperties(properties);

        //Mybatis config파일 위치
        Resource myBatisConfig = new PathMatchingResourcePatternResolver().getResource(configPath);
        sqlSessionFactoryBean.setConfigLocation(myBatisConfig);

        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "mssqlSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate mssqlSqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
