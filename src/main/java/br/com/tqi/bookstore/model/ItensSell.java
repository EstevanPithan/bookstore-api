package br.com.tqi.bookstore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "itens_sell")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItensSell {

    @Id
    private String id;

    @JsonBackReference
    @ManyToMany(mappedBy = "itensSells")//(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "itensSell")
    private List<Book> book = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonManagedReference
    private Client client;

    private LocalDateTime date;
    private double totalPrice;
}
