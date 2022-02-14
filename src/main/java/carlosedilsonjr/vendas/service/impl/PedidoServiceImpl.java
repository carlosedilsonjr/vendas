package carlosedilsonjr.vendas.service.impl;

import org.springframework.stereotype.Service;

import carlosedilsonjr.vendas.repositorio.Pedidos;
import carlosedilsonjr.vendas.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService{
    
    private Pedidos rep;

    public PedidoServiceImpl(Pedidos rep) {
        this.rep = rep;
    }
}
