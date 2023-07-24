package com.vinicius.cinema.controllers;

import com.vinicius.cinema.dtos.IngressoDTO;
import com.vinicius.cinema.services.IngressoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Ingressos")
@RestController
@RequestMapping(value = "/ingresso")
public class IngressoController {

    @Autowired
    private IngressoService ingressoService;

    @ApiOperation("Lista os Ingressos")
    @PreAuthorize("hasRole('ROLE_CLIENTE')")
    @GetMapping
    public ResponseEntity<Page<IngressoDTO>> listar(Pageable pageable){
        return ResponseEntity.ok().body(ingressoService.listar(pageable));
    }
}
