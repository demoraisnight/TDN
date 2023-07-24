package com.vinicius.cinema.dtos;

import com.vinicius.cinema.entities.Usuario;
import com.vinicius.cinema.entities.enums.Role;

public class UsuarioDTO {

    private Long id;
    private String nome;
    private Integer idade;
    private Role role;

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.idade = usuario.getIdade();
        this.role = usuario.getRole();
    }

    public UsuarioDTO(String nome, Integer idade, Role role) {
        this.nome = nome;
        this.idade = idade;
        this.role = role;
    }

    public UsuarioDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


}
