package br.com.tqi.bookstore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "book_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    private String id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonManagedReference
    private Author author;
    @OneToMany
    private List<ItensEntry> itensEntry = new ArrayList<>();
    @JsonManagedReference
    @ManyToMany
    private List<ItensSell> itensSells = new ArrayList<>();

    private String publishingCompany;
    private String image;
    private int year;
    private int quantity = 0;
    private double price = 0;
}
