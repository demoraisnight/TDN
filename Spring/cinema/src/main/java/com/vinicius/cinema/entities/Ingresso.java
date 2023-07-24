package com.vinicius.cinema.entities;

import javax.persistence.*;

@Entity
@Table(name = "tb_ingresso")
public class Ingresso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "filme_id")
    private Filme filme;

    @OneToOne
    private Usuario usuario;

    public Ingresso(Long id, Filme filme, Usuario usuario) {
        this.id = id;
        this.filme = filme;
        this.usuario = usuario;
    }
    public Ingresso() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
