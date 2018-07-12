package it.uniroma2.ticketingsystem.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/components/*").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                .antMatchers("/components/inserisci-utente/inserisci-utente.html").hasAuthority("ROLE_ADMIN")
                .antMatchers("/components/lista-utente/lista-utente.html").hasAuthority("ROLE_ADMIN")
                .antMatchers("/components/dettagli-utente/dettagli-utente.html").hasAuthority("ROLE_ADMIN")
                .antMatchers("/components/visualizza-grafico/visualizza-grafico.html").hasAuthority("ROLE_ADMIN")
                .antMatchers("/components/inserisci-oggetto/inserisci-oggetto.html").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
                .and().csrf().disable().formLogin()
                .loginPage("/login").failureUrl("/login?error")
                .defaultSuccessUrl("/redirect_login")
                .usernameParameter("username")
                .passwordParameter("password")
                .and().logout().logoutSuccessUrl("/login");
    }

}
