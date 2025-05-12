package videocurseapp.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import videocurseapp.demo.Service.UserService;

@Configuration
class SecurityConfig {

    @Autowired
    private UserService userService;

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .formLogin((form) -> form
            .loginPage("/login")
            .defaultSuccessUrl("/home", true)
            .failureUrl("/login/error")
            .permitAll()
        );
        http
        .logout(logout -> logout
            .deleteCookies("JSESSIONID")
        );
        http
            .authorizeRequests(request -> request.requestMatchers(
                new AntPathRequestMatcher("/"))
                .anonymous());
        http
            .authorizeRequests(request -> request.requestMatchers(
                new AntPathRequestMatcher("/singup"))
                .anonymous());
        http
            .authorizeRequests(request -> request.requestMatchers(
                new AntPathRequestMatcher("/about"))
                .anonymous());
        http
            .authorizeRequests(request -> request.requestMatchers(
                new AntPathRequestMatcher("/home"))
                .authenticated());
        http
          .authorizeRequests(request -> request.requestMatchers(
              new AntPathRequestMatcher("/account"))
              .authenticated());
        http
          .authorizeRequests(request -> request.requestMatchers(
              new AntPathRequestMatcher("/help"))
              .authenticated());
		  return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}