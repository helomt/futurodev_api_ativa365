package com.futurodev.ativa365.model.transport;

import com.futurodev.ativa365.model.Person;
import com.futurodev.ativa365.model.enums.GenderEnum;

import java.time.LocalDate;

public record PersonDTO(Long id,
                        String name,
                        GenderEnum gender,
                        String cpf,
                        LocalDate birthday,
                        String email,
                        String cep,
                        String number,
                        String street,
                        String complement) {

    public PersonDTO(Person person){
        this(
                person.getId(),
                person.getName(),
                person.getGender(),
                person.getCpf(),
                person.getBrithday(),
                person.getUsername(),
                person.getCep(),
                person.getNumber(),
                person.getStreet(),
                person.getComplement()
        );
    }
}
