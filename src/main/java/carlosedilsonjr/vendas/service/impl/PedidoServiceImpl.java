package carlosedilsonjr.vendas.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import carlosedilsonjr.vendas.dto.ItemPedidoDTO;
import carlosedilsonjr.vendas.dto.PedidoDTO;
import carlosedilsonjr.vendas.entidades.Cliente;
import carlosedilsonjr.vendas.entidades.ItemPedido;
import carlosedilsonjr.vendas.entidades.Pedido;
import carlosedilsonjr.vendas.entidades.Produto;
import carlosedilsonjr.vendas.enums.StatusPedido;
import carlosedilsonjr.vendas.exception.PedidoNotFoundException;
import carlosedilsonjr.vendas.exception.RegraNegocioException;
import carlosedilsonjr.vendas.repositorio.Clientes;
import carlosedilsonjr.vendas.repositorio.ItemsPedido;
import carlosedilsonjr.vendas.repositorio.Pedidos;
import carlosedilsonjr.vendas.repositorio.Produtos;
import carlosedilsonjr.vendas.service.PedidoService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService{
    
    private final Pedidos rep;
    private final Clientes clientesRep;
    private final Produtos produtosRep;
    private final ItemsPedido itemsPedidoRep;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente =  dto.getCliente();
        Cliente cliente = clientesRep.findById(idCliente)
                                        .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));

        Pedido  pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);

        List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());   
        rep.save(pedido);
        itemsPedidoRep.saveAll(itemsPedido);
        pedido.setItens(itemsPedido);
        return pedido;
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items){
        if(items.isEmpty()){
            throw new RegraNegocioException("Não é possivel realizar um pedido sem itens.");
        }

        return items.stream()
                    .map(dto -> {
                        Integer idProduto = dto.getProduto();
                        Produto produto = produtosRep.findById(idProduto)
                                    .orElseThrow(() -> new RegraNegocioException("Código de produto inválido: " + idProduto));

                        ItemPedido itemPedido = new ItemPedido();
                        itemPedido.setQuantidade(dto.getQuantidade());
                        itemPedido.setPedido(pedido);
                        itemPedido.setProduto(produto);
                        return itemPedido;
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return rep.findByIdFetchItens(id);
    }

    @Override
    @Transactional
    public void attStatus(Integer id, StatusPedido status) {
        
        rep.findById(id)
            .map( pedido -> {
                pedido.setStatus(status);
                return rep.save(pedido);
            }).orElseThrow(() -> new PedidoNotFoundException());
    }
}
