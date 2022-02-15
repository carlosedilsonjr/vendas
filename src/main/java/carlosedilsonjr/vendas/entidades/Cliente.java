package carlosedilsonjr.vendas.entidades;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Cliente")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name="nome", length = 100)
    @NotEmpty(message  ="Campo nome é obrigatório.")
    private String nome;

    @Column(name="cpf", length = 11)
    @NotEmpty(message="Campo CPF é obrigatório.")
    @CPF(message="Informe um CPF válido.")
    private String cpf;

    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private Set<Pedido> pedidos;

    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Cliente [id=" + id + ", nome=" + nome + "]";
    }
}
