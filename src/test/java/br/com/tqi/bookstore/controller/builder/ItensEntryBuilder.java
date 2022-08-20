package br.com.tqi.bookstore.controller.builder;

import br.com.tqi.bookstore.model.ItensEntry;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class ItensEntryBuilder {

    @Builder.Default
    private String id = "123456";

    @Builder.Default
    private String book = "bookid";

    @Builder.Default
    private int quantity = 5;

    @Builder.Default
    private double price = 5;

    @Builder.Default
    private LocalDateTime date = LocalDateTime.now();

    @Builder.Default
    private String observation = "observation";

    @Builder.Default
    private String invoiceNumber = "4321";

    public ItensEntry toItensEntry() {
        return new ItensEntry(id, book, quantity, price, date, observation, invoiceNumber);
    }


}
