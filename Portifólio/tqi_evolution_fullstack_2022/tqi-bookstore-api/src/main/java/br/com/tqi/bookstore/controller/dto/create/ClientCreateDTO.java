package br.com.tqi.bookstore.controller.dto.create;

import lombok.Data;

@Data
public class ClientCreateDTO {

    private String name;
    private String cpf;
    private String email;
}
