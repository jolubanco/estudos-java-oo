package br.com.alura.forum.config.security;

import br.com.alura.forum.modelo.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${forum.jwt.expiration}") //injetando parametros da application.propertirs
    private String expiration;

    @Value("${forum.jwt.secret}") //injetando parametros da application.propertirs
    private String secret;

    public String gerarToken(Authentication authentication) {
        Usuario logado = (Usuario) authentication.getPrincipal(); //devolve o usuário que está logado
        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
        return Jwts.builder()
                .setIssuer("API do Fórum Alura")
                .setSubject(logado.getId().toString()) //seta o identificador único do usuário que está solicitando
                .setIssuedAt(hoje) //data de geração do token
                .setExpiration(dataExpiracao) //tempo de expiração do token
                .signWith(SignatureAlgorithm.HS256,secret) //preciso informar qual o algoritmo de encriptação e qual a senha da minha aplicação
                .compact();
                // o token precisa ser criptografado
    }

    public boolean isTokenValido(String token) {
        //o método parse vai descriptografar o token para verificar se está ok
        //se o token estiver ok ele devolve o objeto caso contrário uma exception, entao vamos tratar a exception
        try{
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getIdUsuario(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }
}
