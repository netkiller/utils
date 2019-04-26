//package cn.netkiller.config;
//
//import com.zaxxer.hikari.HikariDataSource;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class DataSourceConfig {
//
//	@Bean("primaryDataSource")
//	@Qualifier("primaryDataSource")
//	@ConfigurationProperties("spring.datasource.primary")
//	@Primary
//	public DataSource primaryDataSource() {
//		return DataSourceBuilder.create().build();
//	}
//
//	@Bean("secondaryDataSource")
//	@Qualifier("secondaryDataSource")
//	@ConfigurationProperties("spring.datasource.secondary")
//	public DataSource secondaryDataSource() {
//		return DataSourceBuilder.create().build();
//	}
//
//	@Bean("pgsqlDataSource")
//	@ConfigurationProperties("spring.datasource.pgsql")
//	public DataSource pgsqlDataSource() {
//		return DataSourceBuilder.create().build();
//	}
//	
//}