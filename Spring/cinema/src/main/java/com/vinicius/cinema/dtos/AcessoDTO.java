package com.vinicius.cinema.dtos;

import com.vinicius.cinema.entities.Acesso;
import com.vinicius.cinema.entities.enums.Role;

public class AcessoDTO {

    private Long id;
    private String username;


    private UsuarioDTO usuarioDTO;


    public AcessoDTO() {

    }
    public AcessoDTO(Long id, String username, UsuarioDTO usuarioDTO) {
        this.id = id;
        this.username = username;
        this.usuarioDTO = usuarioDTO;
    }

    public AcessoDTO(Acesso acesso) {
        this.id = acesso.getId();
        this.username = acesso.getUsername();
        this.usuarioDTO = new UsuarioDTO(acesso.getUsuario());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UsuarioDTO getUsuarioDTO() {
        return usuarioDTO;
    }

    public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
        this.usuarioDTO = usuarioDTO;
    }
}
