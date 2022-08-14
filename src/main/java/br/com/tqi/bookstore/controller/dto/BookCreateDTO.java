package br.com.tqi.bookstore.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class BookCreateDTO {

    //    @JsonIgnore
    private String title;
    private String author;
    private String publishingCompany;
    private String image;
    private int year;
}
