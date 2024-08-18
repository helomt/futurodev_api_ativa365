package com.futurodev.ativa365.model.transport;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.futurodev.ativa365.model.Person;
import com.futurodev.ativa365.model.enums.GenderEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
@Schema(description = "Classe para armazenar as informações de um usuário")
public record PersonDTO(
        @Schema(description = "Número de identificação do cadastro no banco", example = "1")
        Long id,
        @Schema(description = "Nome do usuário", example = "John Doe")
                        String name,
        @Schema(description = "Gênero do usuário", example = "MALE")
                        GenderEnum gender,
        @Schema(description = "CPF do usuário", example = "00000000001")
                        String cpf,
        @Schema(description = "Data de nascimento", example = "1988-12-25")
                        @JsonFormat(pattern = "dd/MM/yyyy")
                        LocalDate birthday,
        @Schema(description = "Email do usuário", example = "john.doe@exemplo.com")
                        String email,
        @Schema(description = "Cep de endereço do usuário", example = "88035-000")
                        String cep,
        @Schema(description = "Número do endereço do usuário", example = "100")
                        String number,
        @Schema(description = "Rua do endereço do usuário", example = "Avenida A")
                        String street,
        @Schema(description = "Complemento do endereço do usuário", example = "Bloco A")
                        String complement,
        @Schema(description = "Cidade do endereço do usuário", example = "Florianópolis")
                        String city,
        @Schema(description = "Estado do endereço do usuário", example = "Santa Catarina")
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
