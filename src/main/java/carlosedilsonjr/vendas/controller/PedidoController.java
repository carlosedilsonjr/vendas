package carlosedilsonjr.vendas.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import carlosedilsonjr.vendas.service.PedidoService;

@RestController
@RequestMapping("api/pedidos")
public class PedidoController {
    
    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }
}
