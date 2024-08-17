package com.futurodev.ativa365.model.transport;

import com.futurodev.ativa365.model.enums.ActivityEnum;


public record UpdateLocalForm(String name,
                              String description,
                              String cep,
                              String number,
                              String localidade,
                              String logradouro,
                              String uf,
                              String complement,
                              ActivityEnum activity) {
}
