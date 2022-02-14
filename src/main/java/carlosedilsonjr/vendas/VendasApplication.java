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
			clientes.salvar(new Cliente("Carlos"));
			clientes.salvar(new Cliente("Claudia"));
			
			System.out.println("Listando Todos");
			List<Cliente> todosClientes = clientes.obterTodos();
			todosClientes.forEach(System.out::println);

			System.out.println("Atualizando Nomes");
			todosClientes.forEach(c -> {
				c.setNome(c.getNome() + " Atualizado");
				clientes.atualizar(c);
			});

			System.out.println("Buscando Cla");
			clientes.buscarNome("Cla").forEach(System.out::println);

			System.out.println("Listando Todos");
			todosClientes = clientes.obterTodos();
			todosClientes.forEach(System.out::println);

			System.out.println("Deletando e Listando");
			clientes.deletar(1);
			todosClientes = clientes.obterTodos();
			todosClientes.forEach(System.out::println);
		};
	}
		public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}	
}
