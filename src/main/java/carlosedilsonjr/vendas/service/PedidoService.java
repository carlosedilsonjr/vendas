package carlosedilsonjr.vendas.service;

import carlosedilsonjr.vendas.dto.PedidoDTO;
import carlosedilsonjr.vendas.entidades.Pedido;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);
}
