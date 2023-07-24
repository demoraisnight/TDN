package com.vinicius.cinema.services;

import com.vinicius.cinema.dtos.AcessoDTO;
import com.vinicius.cinema.dtos.UsuarioDTO;
import com.vinicius.cinema.dtos.input.AcessoDTOInp;
import com.vinicius.cinema.entities.Acesso;
import com.vinicius.cinema.entities.Usuario;
import com.vinicius.cinema.repositories.AcessoRepository;
import com.vinicius.cinema.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AcessoService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AcessoRepository acessoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Autowired
    public AcessoService(BCryptPasswordEncoder passwordEncoder, AcessoRepository acessoRepository, UsuarioRepository usuarioRepository) {
        this.passwordEncoder = passwordEncoder;
        this.acessoRepository = acessoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public AcessoDTO inserir(AcessoDTOInp acessoDTOInp){
        Acesso entityAcesso = new Acesso();
        Usuario entityUser  = new Usuario();

        //Criando Usuário
        entityUser.setNome(acessoDTOInp.getNomeUsuario());
        entityUser.setRole(acessoDTOInp.getRole());
        entityUser.setIdade(acessoDTOInp.getIdade());
        Usuario usuario = usuarioRepository.save(entityUser);

        //Criando Acesso
        entityAcesso.setUsername(acessoDTOInp.getUsername());
        entityAcesso.setPassword(passwordEncoder.encode(acessoDTOInp.getPassword()));
        entityAcesso.setUsuario(usuario);
        Acesso acesso = acessoRepository.save(entityAcesso);

        //Transformando tudo em DTO
        AcessoDTO acessoDTO = new AcessoDTO();
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);

        acessoDTO.setUsername(acesso.getUsername());
        acessoDTO.setId(acesso.getId());
        acessoDTO.setUsuarioDTO(usuarioDTO);

        return acessoDTO;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Acesso acesso = acessoRepository.findByUsername(username);

        if(acesso == null){
            throw new UsernameNotFoundException("Username Not Found");
        }
        return acesso;
    }

}
