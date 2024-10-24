package com.example.Admin.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public PasswordEncoder getPassEncoded()
    {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/register","/forgot");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.csrf().disable()
                .authorizeRequests()
               .antMatchers("/admin").hasAuthority("ADMIN")
               .antMatchers("/learner").hasAuthority("LEARNER")
               .antMatchers("/mentor").hasAuthority("MENTOR")
               .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/logout-success").permitAll();
    }

    /*  @Bean
    @Override  //In Memory Password And Username with HardCoded Values
    protected UserDetailsService userDetailsService()
    {
        List<UserDetails> users= new ArrayList<>();
        users.add(User.withDefaultPasswordEncoder().username("sumanth").password("1234").roles("USER").build());
        return new InMemoryUserDetailsManager(users);
    }

    @Bean
    public AuthenticationProvider authprovider()
    {
        DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        //provider.setPasswordEncoder(new BCryptPasswordEncoder()); //For BCrypt Encrypted Pass
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance()); //For Plain Text Pass
        return provider;
    }
    */


}
