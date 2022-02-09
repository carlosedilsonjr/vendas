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
			clientes.salvar(new Cliente("Carlos"));
			clientes.salvar(new Cliente("Claudia"));
			
			List<Cliente> todosClientes = clientes.obterTodos();
			todosClientes.forEach(System.out::println);

			todosClientes.forEach(c -> {
				c.setNome(c.getNome() + " Atualizado");
				clientes.atualizar(c);
			});

			todosClientes = clientes.obterTodos();
			todosClientes.forEach(System.out::println);
			
			clientes.deletar(2);

			todosClientes = clientes.obterTodos();
			todosClientes.forEach(System.out::println);
		};
	}
		public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}	
}
