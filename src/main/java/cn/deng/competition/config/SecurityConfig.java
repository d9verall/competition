package cn.deng.competition.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 应用安全配置.
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/7/18 上午2:09
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests(authorize -> authorize
            .antMatchers("/user/exist/**").permitAll()
            .anyRequest().authenticated()
        )
        .formLogin(form ->
            form.loginPage("/login")
                .defaultSuccessUrl("/")
                .permitAll()
        );
  }

  @Override
  public void configure(WebSecurity web) {
    web.ignoring()
        .antMatchers("/static/**");
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
