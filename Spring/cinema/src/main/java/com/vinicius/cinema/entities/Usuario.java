package com.vinicius.cinema.entities;

import com.vinicius.cinema.entities.enums.Role;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "tb_usuario")
public class Usuario implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Integer idade;

    private Role role;

    public Usuario() {
    }

    public Usuario(Long id, String nome, Integer idade, Role role) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.role = role;
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

    @Override
    public String getAuthority() {
        return role.toString();
    }

    public void setAuthority(String authority) {
        this.role = Role.valueOf(authority);
    }

    public boolean hasRole(String roleCliente) {
        if(role.toString() == roleCliente){
            return true;
        }
        else{
            return false;
        }
    }
}
