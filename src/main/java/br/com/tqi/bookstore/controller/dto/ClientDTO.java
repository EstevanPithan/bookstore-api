package br.com.tqi.bookstore.controller.dto;

import br.com.tqi.bookstore.model.ItensSell;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {

    private String id;
    private String name;
    private String cpf;
    private String email;
    private List<ItensSell> itensSell = new ArrayList<>();

}
