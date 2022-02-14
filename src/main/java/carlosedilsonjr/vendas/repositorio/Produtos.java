package carlosedilsonjr.vendas.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import carlosedilsonjr.vendas.entidades.Produto;

public interface Produtos extends JpaRepository<Produto, Integer>{
    
}
