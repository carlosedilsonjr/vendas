package carlosedilsonjr.vendas.repositorio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import carlosedilsonjr.vendas.entidades.Cliente;

@Repository
public class Clientes {
    
    private static String insert = "insert into cliente(nome) values (?)";
    private static String select_all = "select * from cliente";

    @Autowired
    private JdbcTemplate jdbc;

    public Cliente salvar(Cliente cliente){
        jdbc.update(insert, new Object[]{cliente.getNome()});
        return cliente;
    }

    public List<Cliente> obterTodos(){
        return jdbc.query(select_all, new RowMapper<Cliente>(){
            @Override
            public Cliente mapRow(ResultSet rs, int i) throws SQLException {
                return new Cliente(rs.getInt("id"), rs.getString("nome"));
            }
        });
    }
}   
