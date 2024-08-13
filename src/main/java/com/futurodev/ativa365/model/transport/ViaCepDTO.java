package com.futurodev.ativa365.model.transport;

public record ViaCepDTO(String cep,
                        String logradouro,
                        String complemento,
                        String bairro,
                        String localidade,
                        String uf) {
}
