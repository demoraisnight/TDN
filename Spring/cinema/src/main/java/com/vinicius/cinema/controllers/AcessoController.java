package com.vinicius.cinema.controllers;

import com.vinicius.cinema.dtos.AcessoDTO;
import com.vinicius.cinema.dtos.UsuarioDTO;
import com.vinicius.cinema.dtos.input.AcessoDTOInp;
import com.vinicius.cinema.entities.Acesso;
import com.vinicius.cinema.entities.Usuario;
import com.vinicius.cinema.repositories.AcessoRepository;
import com.vinicius.cinema.repositories.UsuarioRepository;
import com.vinicius.cinema.services.AcessoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.URI;

@Api(tags = "Acessos")
@RestController
@RequestMapping(value = "/acesso")
public class AcessoController {

    @Autowired
    private AcessoRepository acessoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AcessoService acessoService;

    @GetMapping("/login_ok")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok("Login successful!");
    }

    @ApiOperation("Cria um novo acesso e usuário")
    @PostMapping
    public ResponseEntity<AcessoDTO> insert(@Valid @RequestBody AcessoDTOInp acessoDTOInp) {
        AcessoDTO dto = acessoService.inserir(acessoDTOInp);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

}
