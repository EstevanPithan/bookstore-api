package br.com.tqi.bookstore.controller.dto.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorCreateDTO {

    private String name;
    private String birthday;

}
