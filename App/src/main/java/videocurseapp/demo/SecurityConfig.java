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
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter.Directive;
import org.springframework.security.web.server.authentication.logout.DelegatingServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.SecurityContextServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.WebSessionServerLogoutHandler;
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
            .permitAll());
        http
            .authorizeRequests(request -> request.requestMatchers(
                new AntPathRequestMatcher("/login/error"))
                .permitAll());

        http 
                .logout(logout -> logout
                        .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
                        .logoutSuccessUrl("/")
                        .logoutUrl("/logout")
                        .permitAll()
                        );
        
        http
            .authorizeRequests(request -> request.requestMatchers(
                new AntPathRequestMatcher("/"))
                .permitAll());
        http
            .authorizeRequests(request -> request.requestMatchers(
                new AntPathRequestMatcher("/signup/**"))
                .permitAll());
        http
            .authorizeRequests(request -> request.requestMatchers(
                new AntPathRequestMatcher("/about"))
                .permitAll());
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
              new AntPathRequestMatcher("/uploadimage"))
              .authenticated());
        
        http
            .authorizeRequests(request -> request.requestMatchers(
              new AntPathRequestMatcher("/upload"))
              .authenticated());
        http
          .authorizeRequests(request -> request.requestMatchers(
              new AntPathRequestMatcher("/help"))
              .authenticated());

        //pruebas
        http
          .authorizeRequests(request -> request.requestMatchers(
              new AntPathRequestMatcher("/images/**"))
              .authenticated());
        http
          .authorizeRequests(request -> request.requestMatchers(
              new AntPathRequestMatcher("/courses/**"))
              .authenticated());
        http
          .authorizeRequests(request -> request.requestMatchers(
              new AntPathRequestMatcher("/videos/**"))
              .authenticated());

        http
          .authorizeRequests(request -> request.requestMatchers(
              new AntPathRequestMatcher("/payexample/**"))
              .authenticated());
        http
          .authorizeRequests(request -> request.requestMatchers(
              new AntPathRequestMatcher("/pay/**"))
              .authenticated());
        //////////////////////////////////////
        //return
		return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("n")
                .password("n")
                .roles("regular")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}