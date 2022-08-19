package br.com.tqi.bookstore.service;

import br.com.tqi.bookstore.exception.IdNotFoundException;
import br.com.tqi.bookstore.exception.NameAlreadyRegisteredException;
import br.com.tqi.bookstore.model.Author;
import br.com.tqi.bookstore.model.Book;
import br.com.tqi.bookstore.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class BookService {

    private final BookRepository bookRepository;

    private final AuthorService authorService;

    public BookService(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Book findById(String id) throws IdNotFoundException {
        return bookRepository.findById(id).orElseThrow(() -> new IdNotFoundException(id)); //Find by id retorna um optinal
    }

    @Transactional
    public Book create(Book bookCreate, String authorId) throws NameAlreadyRegisteredException, IdNotFoundException {
        verifyIfIsAlreadyRegistered(bookCreate.getName());

        String uuid = getUUID();
        Author author = authorService.findById(authorId);
        bookCreate.setId(uuid);
        bookCreate.setAuthor(author);
        bookRepository.save(bookCreate);
        addBookOnAuthorList(bookCreate, author);
        return bookCreate;
    }

    @Transactional
    public void delete(String id) throws IdNotFoundException {
        findById(id);
        bookRepository.deleteById(id);
    }

    @Transactional
    public Book update(String id, Book bookUpdate) throws IdNotFoundException {
        Book book = findById(id);
        book.setName(bookUpdate.getName());
        book.setAuthor(bookUpdate.getAuthor());
        book.setPublishingCompany(bookUpdate.getPublishingCompany());
        book.setImage(bookUpdate.getImage());
        book.setYear(bookUpdate.getYear());
        book.setQuantity(bookUpdate.getQuantity());
        book.setPrice(bookUpdate.getPrice());
        bookRepository.save(book);
        return book;
    }

    public void addBookOnAuthorList(Book book, Author author)throws IdNotFoundException {
        List<Book> bookList = author.getBook();
        bookList.add(book);
        author.setBook(bookList);
        authorService.update(author.getId(), author);
    }


    private void verifyIfIsAlreadyRegistered(String name) throws NameAlreadyRegisteredException {
        Optional<Author> optionalAuthor = bookRepository.findByName(name);
        if (optionalAuthor.isPresent()) {
            throw new NameAlreadyRegisteredException(name);
        }
    }
}
