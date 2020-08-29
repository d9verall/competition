package cn.deng.competition;


import cn.deng.competition.model.entity.SysUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class CompetitionApplicationTests {

  @Test
  void contextLoads() {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String encode = encoder.encode("123456");
    System.out.println(encode);
  }

  @Test
  void userTest () {
    SysUser test = SysUser.builder()
        .name("test")
        .password("test")
        .phone("test")
        .email("test")
        .build();
    SysUser user = SysUser.builder()
        .name("user")
        .password("user")
        .build();

    System.out.println(test);
    System.out.println(user);
    BeanUtils.copyProperties(user, test, "phone", "email");
    System.out.println(user);
  }
}
