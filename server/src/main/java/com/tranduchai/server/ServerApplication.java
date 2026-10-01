package com.tranduchai.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class ServerApplication {

   public static void main(String[] args) {
      SpringApplication.run(ServerApplication.class, args);
   }

}
