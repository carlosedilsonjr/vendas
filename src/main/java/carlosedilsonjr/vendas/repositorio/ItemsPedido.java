package carlosedilsonjr.vendas.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;

import carlosedilsonjr.vendas.entidades.ItemPedido;

public interface ItemsPedido extends JpaRepository<ItemPedido, Integer>{
    
}
