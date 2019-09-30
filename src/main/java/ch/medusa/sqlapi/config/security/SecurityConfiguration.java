package ch.medusa.sqlapi.config.security;

import ch.medusa.sqlapi.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private PasswordEncoder passwordEncoder;
    private UserService userService;
    private JWTProperties jwtProperties;

    @Autowired
    public SecurityConfiguration(PasswordEncoder passwordEncoder, UserService userService, JWTProperties jwtProperties) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.jwtProperties = jwtProperties;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers("/register", "/login", "/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html",
                        "/webjars/**")
                .permitAll().anyRequest().authenticated()
                .and()
                .addFilterAfter(
                        new JWTAuthenticationFilter(new AntPathRequestMatcher("/login", "POST"),
                                authenticationManagerBean(), jwtProperties),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(
                        new JWTAuthorizationFilter(userService, jwtProperties),
                        UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
