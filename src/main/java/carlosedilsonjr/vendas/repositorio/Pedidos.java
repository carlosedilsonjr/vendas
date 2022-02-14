package carlosedilsonjr.vendas.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import carlosedilsonjr.vendas.entidades.Cliente;
import carlosedilsonjr.vendas.entidades.Pedido;

public interface Pedidos extends JpaRepository<Pedido, Integer>{

    List<Pedido> findByCliente(Cliente cliente);
    
}
