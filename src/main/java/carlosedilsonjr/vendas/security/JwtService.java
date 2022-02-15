package carlosedilsonjr.vendas.security;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import carlosedilsonjr.vendas.VendasApplication;
import carlosedilsonjr.vendas.entidades.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {
    
    @Value("${security.jwt.expiracao}")
    private String expiracao;

    @Value("${security.jwt.chave}")
    private String chave;

    public String gerarToken(Usuario usurio){
        
        Long expString = Long.valueOf(expiracao);
        LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expString);
        Date data = Date.from( dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant() );
        return Jwts
                .builder()
                .setSubject(usurio.getLogin())
                .setExpiration(data)
                .signWith(SignatureAlgorithm.HS512, chave)
                .compact();
    }


    private Claims obterClaims(String token) throws ExpiredJwtException {

        return Jwts
                .parser()
                .setSigningKey(chave)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean tokenValido(String token){

        try {
            Claims claim = obterClaims(token);
            Date dataExpiracao = claim.getExpiration();
            LocalDateTime data = dataExpiracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(data);
        } catch (Exception e) {
            return false;
        }
    }


    public String obterLogin(String token) throws ExpiredJwtException {

        return (String) obterClaims(token).getSubject();
    }

    public static void main(String[] args){
        ConfigurableApplicationContext contexto = SpringApplication.run(VendasApplication.class);
        JwtService service = contexto.getBean(JwtService.class);
        Usuario usuario = Usuario.builder().login("fulano").build();
        String token = service.gerarToken(usuario);
        System.out.println(token);

        boolean isTokenValido = service.tokenValido(token);
        System.out.println("O token está valido? "+isTokenValido);
        System.out.println(service.obterLogin(token));
    }
}
