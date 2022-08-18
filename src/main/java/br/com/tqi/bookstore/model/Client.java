package br.com.tqi.bookstore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "client")
public class Client{

    @Id
    private String id;
    private String name;
    private String cpf;
    private String email;

    @JsonBackReference
    @OneToMany//local(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "client")
    private List<ItensSell> itensSell = new ArrayList<>();


}
