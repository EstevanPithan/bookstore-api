package br.com.tqi.bookstore.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDTO {

//    @JsonIgnore
    private String id;
    private String title;
    private String author;
    private String publishingCompany;
    private String image;
    private int year;

    private int quantity = 0;
    private double price = 0;
}
