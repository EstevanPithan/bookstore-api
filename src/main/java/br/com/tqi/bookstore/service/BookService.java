package br.com.tqi.bookstore.service;

import br.com.tqi.bookstore.exception.BookNotFoundException;
import br.com.tqi.bookstore.model.Book;
import br.com.tqi.bookstore.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;


    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Book findById(String id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id)); //Find by id retorna um optinal
    }
    @Transactional
    public Book create(Book bookCreate) {
        String uuid = getUUID();
        bookCreate.setId(uuid);
        bookRepository.save(bookCreate);
        return bookCreate;
    }
    @Transactional
    public void delete(String id) {
        findById(id);
        bookRepository.deleteById(id);
    }
    @Transactional
    public Book update(String id, Book bookUpdate) {
        Book book = findById(id);
        book.setTitle(bookUpdate.getTitle());
        book.setAuthor(bookUpdate.getAuthor());
        book.setPublishingCompany(bookUpdate.getPublishingCompany());
        book.setImage(bookUpdate.getImage());
        book.setYear(bookUpdate.getYear());
        bookRepository.save(book);
        return book;
    }

    @Transactional
    public Book fillStock(String id, double price, int quantity, int invoice) {
        Book book = findById(id);
        book.setPrice(price);
        book.setQuantity(book.getQuantity() + quantity);
        bookRepository.save(book);
        return book;
    }
}
