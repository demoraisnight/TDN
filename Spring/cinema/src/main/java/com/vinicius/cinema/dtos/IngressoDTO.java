package com.vinicius.cinema.dtos;

import com.vinicius.cinema.entities.Filme;
import com.vinicius.cinema.entities.Ingresso;
import com.vinicius.cinema.entities.Usuario;

import javax.persistence.*;

public class IngressoDTO {

    private Long id;

    private FilmeDTO filme;

    private UsuarioDTO usuario;

    public IngressoDTO(Long id, FilmeDTO filme, UsuarioDTO usuario) {
        this.id = id;
        this.filme = filme;
        this.usuario = usuario;
    }
    public IngressoDTO() {

    }
    public IngressoDTO(Ingresso ingresso) {
        this.id = ingresso.getId();
        this.filme = new FilmeDTO(ingresso.getFilme());
        this.usuario = new UsuarioDTO(ingresso.getUsuario());

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FilmeDTO getFilme() {
        return filme;
    }

    public void setFilme(FilmeDTO filme) {
        this.filme = filme;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }
}
