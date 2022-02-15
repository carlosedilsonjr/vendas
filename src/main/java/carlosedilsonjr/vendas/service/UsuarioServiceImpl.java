package carlosedilsonjr.vendas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import carlosedilsonjr.vendas.entidades.Usuario;
import carlosedilsonjr.vendas.repositorio.Usuarios;

@Service
public class UsuarioServiceImpl implements UserDetailsService {

    @Autowired
    private Usuarios usuRep;
    

    @Transactional
    public Usuario salvar(Usuario usuario){
        return usuRep.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuRep.findByLogin(username)
                                .orElseThrow(()->new UsernameNotFoundException("Usuario não encontrado na base de dados."));

        String[] roles = usuario.isAdmin() ? new String[]{"ADMIN", "USER"} : new String[]{"USER"};
        
        return User
                .builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(roles)
                .build();
    }
}
