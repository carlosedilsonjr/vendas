package carlosedilsonjr.vendas.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import carlosedilsonjr.vendas.entidades.Usuario;
import carlosedilsonjr.vendas.repositorio.Usuarios;
import carlosedilsonjr.vendas.service.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    
    private final UsuarioServiceImpl usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final Usuarios usuarios;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@RequestBody @Valid Usuario usuario){
        
        String senhaEncoded = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaEncoded);
        return usuarioService.salvar(usuario);
    }


    @GetMapping("{id}")
    public Usuario mostrUsuario(@PathVariable Integer id){

        return usuarios.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }
}
