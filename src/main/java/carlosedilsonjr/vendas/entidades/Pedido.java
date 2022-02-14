package carlosedilsonjr.vendas.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Pedido {
    
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    private LocalDate dataPedido;
    @Column(length = 20, precision = 20, scale = 2)
    private BigDecimal total;

    @OneToMany(mappedBy="pedido")
    private List<ItemPedido> itens;

    @Override
    public String toString() {
        return "Pedido [dataPedido=" + dataPedido + ", id=" + id + ", total=" + total + "]";
    }    
}
