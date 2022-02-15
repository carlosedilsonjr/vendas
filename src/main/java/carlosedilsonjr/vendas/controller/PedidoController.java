package carlosedilsonjr.vendas.controller;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import carlosedilsonjr.vendas.dto.InfoItemPedidoDTO;
import carlosedilsonjr.vendas.dto.InfoPedidoDTO;
import carlosedilsonjr.vendas.dto.PedidoDTO;
import carlosedilsonjr.vendas.dto.StatusAttDTO;
import carlosedilsonjr.vendas.entidades.ItemPedido;
import carlosedilsonjr.vendas.entidades.Pedido;
import carlosedilsonjr.vendas.enums.StatusPedido;
import carlosedilsonjr.vendas.service.PedidoService;

@RestController
@RequestMapping("api/pedidos")
public class PedidoController {
    
    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody @Valid PedidoDTO dto){
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }

    @GetMapping("{id}")
    public InfoPedidoDTO getById(@PathVariable Integer id){

        return service.obterPedidoCompleto(id)
                        .map(p -> converter(p))
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
    }

    private InfoPedidoDTO converter(Pedido pedido){

        return InfoPedidoDTO.builder()
                        .codigo(pedido.getId())
                        .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                        .cpf(pedido.getCliente().getCpf())
                        .nome(pedido.getCliente().getNome())
                        .total(pedido.getTotal())
                        .status(pedido.getStatus().name())
                        .items(converter2(pedido.getItens()))
                        .build();
    }

    private List<InfoItemPedidoDTO> converter2(List<ItemPedido> items){

        if(CollectionUtils.isEmpty(items)){
            return Collections.emptyList();
        }

        return items.stream().map(item -> InfoItemPedidoDTO.builder()
                                                            .descricao(item.getProduto().getDescricao())
                                                            .preco(item.getProduto().getPreco())
                                                            .quantidade(item.getQuantidade())
                                                            .build())
                                                            .collect(Collectors.toList());
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelPedido(@PathVariable Integer id, @RequestBody StatusAttDTO dto){

        String novoStatus = dto.getNovoStatus();
        service.attStatus(id, StatusPedido.valueOf(novoStatus));
    }


}
