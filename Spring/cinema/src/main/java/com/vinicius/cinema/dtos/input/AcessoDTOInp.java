package com.vinicius.cinema.dtos.input;

import com.vinicius.cinema.entities.enums.Role;
import com.vinicius.cinema.services.validation.UserInsertValid;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@UserInsertValid
public class AcessoDTOInp {

    @NotBlank(message = "Campo Requerido")
    private String nomeUsuario;
    @NotNull(message = "Campo Requerido")
    private Role role;
    @Positive(message = "O valor precisa ser positivo")
    @NotNull(message = "Campo Requerido")
    private Integer idade;
    @NotBlank(message = "Campo Requerido")
    private String username;
    @NotBlank(message = "Campo Requerido")
    private String password;

    public AcessoDTOInp(String nomeUsuario, Role role, Integer idade, String username, String password) {
        this.nomeUsuario = nomeUsuario;
        this.role = role;
        this.idade = idade;
        this.username = username;
        this.password = password;
    }


    public AcessoDTOInp() {

    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
