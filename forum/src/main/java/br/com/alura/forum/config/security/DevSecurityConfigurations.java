package br.com.alura.forum.config.security;

import br.com.alura.forum.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity //habilitando o método de segurança do spring
@Configurable //o spring le as configurações que faremos do projeto no momento da inicialização
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    //Configurações de Autenticação
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder()); // alem de fazer a verificaxao de autenticação, encripta a senha no banco
        //preciso passar a classe service que terá a logica de autenticacao
    }

    //Configurações de Autorização
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers(HttpMethod.GET,"/topicos").permitAll() //desta forma, só liberamos o acesso para os métodos GET
        .antMatchers(HttpMethod.GET, "/topicos/*").permitAll()
        .antMatchers(HttpMethod.POST, "/auth").permitAll()
        .antMatchers(HttpMethod.GET, "/actuator/**").permitAll() //em produção não podemos deixar permitAll, porque devolve informações sensíveis
        .antMatchers(HttpMethod.DELETE, "/topicos/*").hasRole("MODERADOR") //restringindo o acesso pelo perfil do usuário
        .anyRequest().authenticated() //qualquer outra requisicao não informada precisará de autenticação
        .and().csrf().disable() //desbilita uma ferramente que evita ataques hackers, pois nao vamos fazer a autorização via web
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //agora o spring não vai criar a seção
        .and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService,usuarioRepository), UsernamePasswordAuthenticationFilter.class);
    }

//    //Configurações de Autorização
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers(HttpMethod.GET,"/topicos").permitAll() //desta forma, só liberamos o acesso para os métodos GET
//                .antMatchers(HttpMethod.GET, "/topicos/*").permitAll()
//                .anyRequest().authenticated() //qualquer outra requisicao não informada precisará de autenticação
//                .and().formLogin(); //criar uma tela do spring para realizar o login de autorização
//    }

    //Configurações de recursos estáticos(javascript,css,imagem, swagger, etc...) normalmente usada quando a aplicação front-end está inclusa no back, o que não é o caso
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
    }

//    public static void main(String[] args) {
//        System.out.println(new BCryptPasswordEncoder().encode("123456"));
//    }
}
