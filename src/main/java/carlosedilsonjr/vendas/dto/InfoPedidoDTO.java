package carlosedilsonjr.vendas.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InfoPedidoDTO {
    
    private Integer codigo;
    private String cpf;
    private String nome;
    private BigDecimal total;
    private String dataPedido;
    private String status;
    private List<InfoItemPedidoDTO> items;
}
