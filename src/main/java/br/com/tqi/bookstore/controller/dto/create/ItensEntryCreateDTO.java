package br.com.tqi.bookstore.controller.dto.create;

import lombok.Data;

@Data
public class ItensEntryCreateDTO {

    private String bookId;
    private int quantity;
    private double price;
    private String observation;
    private String invoiceNumber;
}