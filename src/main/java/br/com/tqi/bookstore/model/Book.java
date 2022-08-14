package br.com.tqi.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "book_Database")
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Entity
public class Book {

    @Id
    private String id;
    private String title;
    private String author;
    private String publishingCompany;
    private String image;
    private int year;

    private int quantity = 0;
    private double price = 0;
}
