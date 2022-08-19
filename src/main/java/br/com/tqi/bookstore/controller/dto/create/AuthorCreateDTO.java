package br.com.tqi.bookstore.controller.dto.create;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorCreateDTO {

    private String name;
    private String birthday;

}
