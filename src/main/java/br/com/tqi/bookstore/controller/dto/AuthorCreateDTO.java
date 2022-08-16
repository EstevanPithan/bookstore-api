package br.com.tqi.bookstore.controller.dto;

import lombok.Data;

@Data
public class AuthorCreateDTO {


    private String name;
    private String description;
    private String image;

}
