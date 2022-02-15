package carlosedilsonjr.vendas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import carlosedilsonjr.vendas.ApiErrors;
import carlosedilsonjr.vendas.exception.PedidoNotFoundException;
import carlosedilsonjr.vendas.exception.RegraNegocioException;

@RestControllerAdvice
public class ApplicationControllerAdvice {
    
    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRNException(RegraNegocioException ex){

        String mensagemErro = ex.getMessage();
        return new ApiErrors(mensagemErro);
    }

    @ExceptionHandler(PedidoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handlePedidoNFException(PedidoNotFoundException ex){

        return new ApiErrors(ex.getMessage());
    }
}
