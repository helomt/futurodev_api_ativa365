package com.futurodev.ativa365.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI configureOpenAPI(){
        Contact contact =  new Contact()
                .name("Heloisa Martins Tavares")
                .email("heloisamt@gmail.com")
                .url("https://www.linkedin.com/in/heloisamtavares/");

        Info info =  new Info()
                .title("Ativa365")
                .description("O Ativa365 é uma plataforma que facilita o gerenciamento de " +
                        "exercícios e locais para atividades físicas serem praticadas. " +
                        "\nOs usuários podem cadastrar novos locais de exercícios, encontrar pontos " +
                        "próximos em um mapa interativo (ou lista), visualizar informações sobre os " +
                        "exercícios em cada ponto e registrar suas próprias contribuições para o sistema.")
                .version("1.0.0")
                .contact(contact);

        return new OpenAPI().components(new Components()).info(info);

    }
}
