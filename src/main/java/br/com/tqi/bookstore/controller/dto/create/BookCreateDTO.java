package br.com.tqi.bookstore.controller.dto.create;

import br.com.tqi.bookstore.model.Author;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import javax.swing.*;

@Data
public class BookCreateDTO {

    private String name;
    private String authorId;
    private String publishingCompany;
    private String image;
    private int year;
}
