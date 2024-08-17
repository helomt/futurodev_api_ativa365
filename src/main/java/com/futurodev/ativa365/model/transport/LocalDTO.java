package com.futurodev.ativa365.model.transport;

import com.futurodev.ativa365.model.Local;
import com.futurodev.ativa365.model.enums.ActivityEnum;

public record LocalDTO(Long id,
                       String name,
                       String description,
                       String cep,
                       String number,
                       String city,
                       String street,
                       String state,
                       String complement,
                       ActivityEnum activity,
                       PersonDTO owner) {

    public LocalDTO(Local local){
        this(
                local.getId(),
                local.getName(),
                local.getDescription(),
                local.getCep(),
                local.getNumber(),
                local.getCity(),
                local.getStreet(),
                local.getState(),
                local.getComplement(),
                local.getActivity(),
                new PersonDTO(local.getOwner())
        );
    }
}
