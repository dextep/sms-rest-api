package pl.popiel.sms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private CustomUserDetailsService userDetailsService;


    @Override
    protected void configure (AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic().and().authorizeRequests().anyRequest().authenticated().and().formLogin().permitAll().and().csrf().disable();
        http
                .httpBasic()
                .and()
                .cors()
                .and()
                .csrf()
                    .disable()
                .authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/login").permitAll()
                    .antMatchers("/signup").permitAll()
                    .antMatchers("/dashboard/**").hasAuthority("ADMIN")
                .anyRequest()
                    .authenticated()
                .and()
                .formLogin()
//                .loginPage("/login")
                    .permitAll()
                    .failureUrl("/login?error=true")
                    .usernameParameter("email")
                    .passwordParameter("password")
//                    .successForwardUrl("/")
                .and()
                .logout()
                    .permitAll()
                    .deleteCookies("JSESSIONID")
//                    .logoutSuccessUrl("/")
                .and()
                    .exceptionHandling();
    }


    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                "/resources/**", "/static/**", "/css/**", "/js/**", "/images/**",
                "/resources/static/**", "/css/**", "/js/**", "/img/**", "/fonts/**",
                "/images/**", "/scss/**", "/vendor/**", "/favicon.ico", "/auth/**", "/favicon.png",
                "/v2/api-docs", "/configuration/ui", "/configuration/security", "/swagger-ui.html",
                "/webjars/**", "/swagger-resources/**", "/swagge‌​r-ui.html", "/actuator",
                "/actuator/**");
    }

}
