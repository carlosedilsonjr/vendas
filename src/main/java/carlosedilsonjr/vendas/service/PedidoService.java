package carlosedilsonjr.vendas.service;

import java.util.Optional;

import carlosedilsonjr.vendas.dto.PedidoDTO;
import carlosedilsonjr.vendas.entidades.Pedido;
import carlosedilsonjr.vendas.enums.StatusPedido;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);
    void attStatus(Integer id, StatusPedido status );
}
