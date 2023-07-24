package com.vinicius.cinema.services.validation;

import com.vinicius.cinema.controllers.exceptions.FieldMessage;
import com.vinicius.cinema.dtos.input.AcessoDTOInp;
import com.vinicius.cinema.entities.Acesso;
import com.vinicius.cinema.entities.Usuario;
import com.vinicius.cinema.repositories.AcessoRepository;
import com.vinicius.cinema.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, AcessoDTOInp> {

    @Autowired
    private AcessoRepository repository;

    @Override
    public void initialize(UserInsertValid ann) {
    }

    @Override
    public boolean isValid(AcessoDTOInp dto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        Acesso acesso = repository.findByUsername(dto.getUsername());
        if (acesso != null) {
            list.add(new FieldMessage("username", "Username já existe"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}