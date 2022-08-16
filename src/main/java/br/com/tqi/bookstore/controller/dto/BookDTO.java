package br.com.tqi.bookstore.controller.dto;

import br.com.tqi.bookstore.model.Author;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDTO {

    private String id;
    private String name;
    private Author author;
    private String publishingCompany;
    private String image;
    private int year;

    private int quantity = 0;
    private double price = 0;
}
