package com.etiennek.elec;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class App {

  public static void main(String[] args) throws Exception {
    if (System.getenv("DATABASE_URL") != null) {
      args = herokuConfiguration(args);
    }
    SpringApplication.run(App.class, args);
  }

  private static String[] herokuConfiguration(String[] args) {
    URI dbUri = null;
    try {
      dbUri = new URI(System.getenv("DATABASE_URL"));
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    String[] credentials = dbUri.getUserInfo()
                                .split(":");
    String username = credentials[0];
    String password;
    if (credentials.length > 1) {
      password = credentials[1];
    } else {
      password = "";
    }

    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

    List<String> argList = new ArrayList<>(Arrays.asList(args));
    argList.add("--dsUrl=" + dbUrl);
    argList.add("--dsUsername=" + username);
    argList.add("--dsPassword=" + password);

    return argList.toArray(new String[argList.size()]);
  }

  public DataSource dataSource() {
    String databaseUrl = System.getenv("DATABASE_URL");
    URI dbUri = null;
    try {
      dbUri = new URI(databaseUrl);
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    String[] credentials = dbUri.getUserInfo()
                                .split(":");
    String username = credentials[0];
    String password;
    if (credentials.length > 1) {
      password = credentials[1];
    } else {
      password = "";
    }

    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

    BasicDataSource basicDataSource = new BasicDataSource();
    basicDataSource.setUrl(dbUrl);
    basicDataSource.setUsername(username);
    basicDataSource.setPassword(password);

    return basicDataSource;
  }
}
