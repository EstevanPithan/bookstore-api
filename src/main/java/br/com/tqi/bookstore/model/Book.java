package br.com.tqi.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "book_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book  {

    @Id
    private String id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    private String publishingCompany;
    private String image;
    private int year;
    private int quantity = 0;
    private double price = 0;
}
