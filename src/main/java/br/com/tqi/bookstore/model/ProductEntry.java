package br.com.tqi.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Date;

@Entity(name = "book_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntry {

    private Date date;
    private String observation;


}
