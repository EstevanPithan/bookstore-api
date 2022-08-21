package br.com.tqi.bookstore.controller.dto;

import br.com.tqi.bookstore.model.Author;
import br.com.tqi.bookstore.model.Book;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorDTO {

    private String id;
    private String name;
    private String birthday;
 }
