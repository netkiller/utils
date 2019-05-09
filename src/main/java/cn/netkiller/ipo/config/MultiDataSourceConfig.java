package cn.netkiller.ipo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class MultiDataSourceConfig {

	@Bean
	@Primary
	@ConfigurationProperties("spring.datasource")
	public DataSourceProperties defaultDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	@Primary
	@ConfigurationProperties("spring.datasource")
	public DataSource defaultDataSource() {
		return defaultDataSourceProperties().initializeDataSourceBuilder().build();
	}

	@Bean("JdbcTemplate")
	@Primary
	public JdbcTemplate defaultJdbcTemplate(@Qualifier("defaultDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	@ConfigurationProperties("spring.datasource.input")
	public DataSourceProperties inputDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean("Input")
	@ConfigurationProperties("spring.datasource.input")
	public DataSource inputDataSource() {
		return inputDataSourceProperties().initializeDataSourceBuilder().build();
	}

	@Bean("inputJdbcTemplate")
	public JdbcTemplate inputJdbcTemplate(@Qualifier("Input") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	@ConfigurationProperties("spring.datasource.output")
	public DataSourceProperties outputDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean(name = "Output")
	@ConfigurationProperties("spring.datasource.output")
	public DataSource outputDataSource() {
		return outputDataSourceProperties().initializeDataSourceBuilder().build();
	}

	@Bean("outputJdbcTemplate")
	public JdbcTemplate outputJdbcTemplate(@Qualifier("Output") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

}