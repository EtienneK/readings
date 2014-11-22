package com.etiennek.elec;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class App {

  public static void main(String[] args) throws Exception {
    SpringApplication.run(App.class, args);
  }

  @Bean
  public Connection getConnection() throws URISyntaxException, SQLException {
    String databaseUrl = System.getenv("DATABASE_URL");
    if (databaseUrl == null) {
      return null;
    }

    URI dbUri = new URI(System.getenv("DATABASE_URL"));
    String username = dbUri.getUserInfo()
                           .split(":")[0];
    String password = dbUri.getUserInfo()
                           .split(":")[1];
    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
    return DriverManager.getConnection(dbUrl, username, password);
  }
}
