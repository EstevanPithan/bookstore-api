package br.com.tqi.bookstore.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ItensSellDTO {

    private String id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime date;
    private double totalPrice;
    private String[] bookIds;
    private Integer[] booksQnt;

    private String clientId;
}
