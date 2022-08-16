package br.com.tqi.bookstore.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {

    private String id;
    private String name;
    private String cpf;
    private String email;

}
