package com.vinicius.cinema.services;

import com.vinicius.cinema.dtos.IngressoDTO;
import com.vinicius.cinema.entities.Acesso;
import com.vinicius.cinema.entities.Ingresso;
import com.vinicius.cinema.repositories.AcessoRepository;
import com.vinicius.cinema.repositories.IngressoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IngressoService {

    @Autowired
    private IngressoRepository ingressoRepository;

    @Autowired
    private AcessoRepository usuarioRepository;

    @Transactional(readOnly = true)
    public Page<IngressoDTO> listar(Pageable pageable) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Acesso acesso = usuarioRepository.findByUsername(username);

        List<Ingresso> todosIngressosLista = ingressoRepository.findByUsuario(acesso.getUsuario());
        Page<Ingresso> todosIngressos = new PageImpl<>(todosIngressosLista, pageable, todosIngressosLista.size());

        return todosIngressos.map(x -> new IngressoDTO(x));
    }
}
