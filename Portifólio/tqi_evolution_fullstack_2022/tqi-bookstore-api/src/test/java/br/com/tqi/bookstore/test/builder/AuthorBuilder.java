//package br.com.tqi.bookstore.test.builder;
//
//import br.com.tqi.bookstore.model.Author;
//import br.com.tqi.bookstore.model.Book;
//import br.com.tqi.bookstore.model.ItensEntry;
//import br.com.tqi.bookstore.model.ItensSell;
//import lombok.Builder;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.List;
//
//@Builder
//public class AuthorBuilder {
//
//    @Builder.Default
//    private String id = "123456";
//
//    @Builder.Default
//    private String name = "author for test";
//
//    @Builder.Default
//    private String birthday = "14/10/1994";
//
//    @Builder.Default
//    private List<Book> book = new ArrayList<>(Collections.singleton(new Book("123456",
//            "book test",
//            new Author(),
//            new ArrayList<ItensEntry>(),
//            new ArrayList<ItensSell>(),
//            "company test",
//            "image url",
//            1994,
//            0,
//            0)));
//
//    public Author toAuthor() {
//        return new Author(id, name, birthday, book);
//    }
//}
