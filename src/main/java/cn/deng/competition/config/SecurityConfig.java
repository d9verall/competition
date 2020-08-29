package cn.deng.competition.config;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * 应用安全配置.
 *
 * @author verall
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private static final String LOGIN_URL = "/login";

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests(authorize -> authorize
            .antMatchers("/").authenticated()
            .anyRequest().permitAll()
        )
        .formLogin(form ->
            form.loginPage(LOGIN_URL)
                .defaultSuccessUrl("/dashboard")
                .failureHandler((request, response, exception) ->
                    response
                        .sendRedirect(buildErrorPath(request.getParameter("username"), exception)))
                .permitAll()
        )
        .logout(logout ->
            logout
                .invalidateHttpSession(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        )
        .csrf();
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

  private String buildErrorPath(String username, Exception exception) {
    return LOGIN_URL +
        "?error=" + URLEncoder.encode(exception.getLocalizedMessage(), StandardCharsets.UTF_8) +
        "&username=" + username;
  }

}
