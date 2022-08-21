package br.com.tqi.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity(name = "itens_sell")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItensSell {

    @Id
    private String id;
    private String[] bookIds;
    private Integer[] booksQnt;
    private String clientId;
    private LocalDateTime date;
    private double totalPrice;
}
