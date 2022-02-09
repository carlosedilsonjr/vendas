package carlosedilsonjr.vendas;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {
    
    @Bean
    public String appName(){
        return "Sistema de Vendas";
    }
}
