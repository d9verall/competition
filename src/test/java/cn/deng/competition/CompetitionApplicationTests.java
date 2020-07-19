package cn.deng.competition;


import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class CompetitionApplicationTests {

  @Test
  void contextLoads() {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String encode = encoder.encode("123456");
    System.out.println(encode);
  }

}
