package cn.deng.competition.config;

import java.security.Principal;
import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author verall
 */
@Configuration
public class ApplicationConfig {

  /**
   * Jpa 代码审计
   * 自动添加创建/修改用户
   *
   * @return AuditorAware
   */
  @Bean
  public AuditorAware<String> userAuditorHandle() {
    return () -> Optional.ofNullable(SecurityContextHolder.getContext())
        .map(SecurityContext::getAuthentication)
        .map(Principal::getName);
  }

}
