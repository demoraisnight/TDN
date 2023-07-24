package com.vinicius.cinema.controllers;

import com.vinicius.cinema.dtos.FilmeDTO;
import com.vinicius.cinema.dtos.IngressoDTO;
import com.vinicius.cinema.dtos.input.FilmeDTOInp;
import com.vinicius.cinema.services.FilmeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@Api(tags = "Filmes")
@RestController
@RequestMapping(value = "/filme")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @ApiOperation("Lista os filmes")
    @PreAuthorize("hasAnyRole('ROLE_FUNCIONARIO', 'ROLE_CLIENTE')")
    @GetMapping
    public ResponseEntity<Page<FilmeDTO>> listar(Pageable pageable){
        return ResponseEntity.ok().body(filmeService.listar(pageable));
    }


    @ApiOperation("Compra ingressos")
    @PreAuthorize("hasRole('ROLE_CLIENTE')")
    @PutMapping(value = "/{idFilme}")
    public ResponseEntity<IngressoDTO> comprarIngresso(@PathVariable Long idFilme){
        IngressoDTO dto = filmeService.comprarIngresso(idFilme);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @ApiOperation("Insere novos filmes")
    @PreAuthorize("hasRole('ROLE_FUNCIONARIO')")
    @PostMapping
    public ResponseEntity<FilmeDTO> inserir(@Valid @RequestBody FilmeDTOInp filmeDTOInp) {
        FilmeDTO dto = filmeService.inserir(filmeDTOInp);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }


    @ApiOperation("Remove um filme")
    @PreAuthorize("hasRole('ROLE_FUNCIONARIO')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        filmeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
