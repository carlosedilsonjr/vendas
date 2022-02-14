package carlosedilsonjr.vendas;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import carlosedilsonjr.vendas.entidades.Cliente;
import carlosedilsonjr.vendas.repositorio.Clientes;

@SpringBootApplication
@RestController
public class VendasApplication {

	
	@Bean
	public CommandLineRunner init(@Autowired Clientes clientes){
		return args -> {
			System.out.println("Salvando Cliente");
			clientes.save(new Cliente("Carlos"));
			clientes.save(new Cliente("Claudia"));
			
			System.out.println("Listando Todos");
			List<Cliente> todosClientes = clientes.findAll();
			todosClientes.forEach(System.out::println);
		};
	}
		public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}	
}
