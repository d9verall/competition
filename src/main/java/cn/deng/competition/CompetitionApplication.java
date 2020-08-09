package cn.deng.competition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author verall
 * @date 2020/7/14 下午11:39
 */
@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing
public class CompetitionApplication {

  public static void main(String[] args) {
    SpringApplication.run(CompetitionApplication.class, args);
  }

}
