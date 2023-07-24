package com.vinicius.cinema.dtos.input;

import com.vinicius.cinema.entities.enums.Tecnologia;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class FilmeDTOInp {

    @NotBlank(message = "Campo requerido")
    private String titulo;

    @NotBlank(message = "Campo requerido")
    private String genero;

    @Positive(message = "Deve ser um numero inteiro e positivo")
    @NotNull(message = "Campo requerido")
    private Integer idadeMinima;

    @Positive(message = "Deve ser um numero inteiro e positivo")
    @NotNull(message = "Campo requerido")
    private Integer tempo;

    @Positive(message = "O valor precisa ser positivo")
    @NotNull(message = "Campo requerido")
    private BigDecimal valor;

    @NotNull(message = "Campo requerido")
    private Tecnologia tecnologia;

    public FilmeDTOInp(String titulo, String genero, Integer idadeMinima, Integer tempo, BigDecimal valor, Tecnologia tecnologia) {
        this.titulo = titulo;
        this.genero = genero;
        this.idadeMinima = idadeMinima;
        this.tempo = tempo;
        this.valor = valor;
        this.tecnologia = tecnologia;
    }

    public FilmeDTOInp() {

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getIdadeMinima() {
        return idadeMinima;
    }

    public void setIdadeMinima(Integer idadeMinima) {
        this.idadeMinima = idadeMinima;
    }

    public Integer getTempo() {
        return tempo;
    }

    public void setTempo(Integer tempo) {
        this.tempo = tempo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Tecnologia getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(Tecnologia tecnologia) {
        this.tecnologia = tecnologia;
    }
}
