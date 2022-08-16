package br.com.tqi.bookstore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity(name = "author_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @Id
    private String id;
    private String name;
    private String description;
    private String image;
    @JsonBackReference
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "author")
    private List<Book> book = new ArrayList<>();
}