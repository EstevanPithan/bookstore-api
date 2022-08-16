package br.com.tqi.bookstore.controller.dto;

import lombok.Data;

@Data
public class ItensEntryCreateDTO {

    private String book;
    private int quantity;
    private double price;
    private String observation;
}