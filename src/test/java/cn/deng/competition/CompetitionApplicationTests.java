package cn.deng.competition;


import cn.deng.competition.model.entity.SysUser;
import cn.deng.competition.repostiory.SysUserRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@DataJpaTest
class CompetitionApplicationTests {

  @Autowired
  private SysUserRepository sysUserRepository;

  @BeforeEach
  void setUp() {
    SysUser sysUser = SysUser.builder()
        .name("test")
        .email("test")
        .phone("test")
        .build();
    sysUserRepository.save(sysUser);
  }

  @Test
  void contextLoads() {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String encode = encoder.encode("123456");
    System.out.println(encode);
  }

  @Test
  void userTest () {
    List<SysUser> users = sysUserRepository.findAll();
    System.out.println(users);
  }
}
