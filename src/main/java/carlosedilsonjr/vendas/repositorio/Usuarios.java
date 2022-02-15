package carlosedilsonjr.vendas.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import carlosedilsonjr.vendas.entidades.Usuario;

@Repository
public interface Usuarios extends JpaRepository<Usuario, Integer> {
    
    Optional<Usuario> findByLogin(String login);
}
