package carlosedilsonjr.vendas.exception;

public class PedidoNotFoundException extends RuntimeException{

    public PedidoNotFoundException() {
        super("Pedido não encontrado.");
    }

    
}
