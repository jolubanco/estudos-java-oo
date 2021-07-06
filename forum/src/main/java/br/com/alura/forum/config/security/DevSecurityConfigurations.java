package br.com.alura.forum.config.security;

import br.com.alura.forum.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity //habilitando o método de segurança do spring
@Configuration //o spring le as configurações que faremos do projeto no momento da inicialização
@Profile("dev") //informa para o spring carregar essa classe de configurações apenas se o profile for de 'dev'
//liberando todas as urls no ambiente de desenvolvimento para garantir a eficiencia do desenvolvimento
public class DevSecurityConfigurations extends WebSecurityConfigurerAdapter {

    //Configurações de Autorização
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers("/**").permitAll() //desta forma, só liberamos o acesso para os métodos GET
        .and().csrf().disable(); //desbilita uma ferramente que evita ataques hackers, pois nao vamos fazer a autorização via web
    }
}
