package br.com.tqi.bookstore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "author_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @Id
    private String id;
    private String name;
    private String birthday;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "author")
    private List<Book> book = new ArrayList<>();
}