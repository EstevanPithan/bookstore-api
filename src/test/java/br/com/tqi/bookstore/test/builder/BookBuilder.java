package br.com.tqi.bookstore.test.builder;

import br.com.tqi.bookstore.model.Author;
import br.com.tqi.bookstore.model.Book;
import br.com.tqi.bookstore.model.ItensEntry;
import br.com.tqi.bookstore.model.ItensSell;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
public class BookBuilder {

    @Builder.Default
    private String id = "123456";

    @Builder.Default
    private String name = "book test";

//    @Builder.Default
//    private Author author = new Author("123456", "author for test", "14/10/1994",  new ArrayList<Book>());

    @Builder.Default
    private String author = "auhtor";

    @Builder.Default
    private String publishingCompany = "company for book test";

    @Builder.Default
    private List<ItensSell> itensSells = new ArrayList<>();

    @Builder.Default
    private List<ItensEntry> itensEntry = new ArrayList<>();

    @Builder.Default
    private String image = "image for book test";

    @Builder.Default
    private int year = 1994;

    @Builder.Default
    private int quantity = 0;

    @Builder.Default
    private double price = 0;

    public Book toBook() {
        return new Book(id, name, author, itensEntry, itensSells, publishingCompany, image, year, quantity, price);
    }
}
