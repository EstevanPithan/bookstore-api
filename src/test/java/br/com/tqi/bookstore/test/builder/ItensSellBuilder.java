package br.com.tqi.bookstore.test.builder;

import br.com.tqi.bookstore.model.ItensSell;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class ItensSellBuilder {

    @Builder.Default
    private String id = "123456";

    @Builder.Default
    private String[] bookIds = {"123456"};

    @Builder.Default
    private Integer[] booksQnt = {1};

    @Builder.Default
    private String clientId = "123456";

    @Builder.Default
    private LocalDateTime date = LocalDateTime.now();

    @Builder.Default
    private double totalPrice = 30;

    public ItensSell toItensSell(){
        return new ItensSell(id,bookIds,booksQnt,clientId,date,totalPrice);
    }
}
