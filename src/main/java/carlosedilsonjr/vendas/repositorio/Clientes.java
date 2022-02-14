package carlosedilsonjr.vendas.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import carlosedilsonjr.vendas.entidades.Cliente;


public interface Clientes extends JpaRepository<Cliente, Integer> {

    List<Cliente> findByNomeLike(String nome);
   
}   
