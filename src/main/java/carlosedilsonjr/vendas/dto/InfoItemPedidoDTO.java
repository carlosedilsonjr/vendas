package carlosedilsonjr.vendas.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InfoItemPedidoDTO {

    private String descricao;
    private BigDecimal preco;
    private Integer quantidade;
}
