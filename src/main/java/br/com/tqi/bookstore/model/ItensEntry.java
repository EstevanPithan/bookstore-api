package br.com.tqi.bookstore.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "itens_entry")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItensEntry {

    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "book_id")
    @JsonManagedReference
    private Book book;

    private int quantity = 0;
    private double price = 0;
    private Date date;
    private String observation;
    private String invoiceNumber;

}
