package com.futurodev.ativa365.model.transport;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.futurodev.ativa365.model.Person;
import com.futurodev.ativa365.model.enums.GenderEnum;

import java.time.LocalDate;

public record PersonDTO(Long id,
                        String name,
                        GenderEnum gender,
                        String cpf,
                        @JsonFormat(pattern = "dd/MM/yyyy")
                        LocalDate birthday,
                        String email,
                        String cep,
                        String number,
                        String street,
                        String complement,
                        String city,
                        String state) {

    public PersonDTO(Person person){
        this(
                person.getId(),
                person.getName(),
                person.getGender(),
                person.getCpf(),
                person.getBirthday(),
                person.getUsername(),
                person.getCep(),
                person.getNumber(),
                person.getStreet(),
                person.getComplement(),
                person.getCity(),
                person.getState()
        );
    }
}
