package br.com.tqi.bookstore.controller.dto.create;

import lombok.Data;

@Data
public class ItensSellCreateDTO {

    private String[] bookIds;
    private Integer[] booksQnt;
    private double totalPrice;
    private String clientId;
}
