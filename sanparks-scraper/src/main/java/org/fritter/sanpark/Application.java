package org.fritter.sanpark;

import org.fritter.sanpark.scraper.Scraper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
    return args -> {
      final Scraper scraper = ctx.getBean(Scraper.class);

      scraper.scrap("2019-11-11", "2019-11-13"
          + "");
      scraper.scrap("2019-11-10", "2019-11-12");

    };
  }

}
