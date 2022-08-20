package br.com.tqi.bookstore.controller.service;

import br.com.tqi.bookstore.controller.builder.AuthorBuilder;
import br.com.tqi.bookstore.controller.builder.BookBuilder;
import br.com.tqi.bookstore.controller.dto.BookDTO;
import br.com.tqi.bookstore.controller.mapper.AuthorMapper;
import br.com.tqi.bookstore.controller.mapper.BookMapper;
import br.com.tqi.bookstore.exception.IdNotFoundException;
import br.com.tqi.bookstore.exception.NameAlreadyRegisteredException;
import br.com.tqi.bookstore.model.Author;
import br.com.tqi.bookstore.model.Book;
import br.com.tqi.bookstore.model.ItensEntry;
import br.com.tqi.bookstore.model.ItensSell;
import br.com.tqi.bookstore.repository.AuthorRepository;
import br.com.tqi.bookstore.repository.BookRepository;
import br.com.tqi.bookstore.service.AuthorService;
import br.com.tqi.bookstore.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorService authorService;
    private BookMapper bookMapper;
    @InjectMocks
    private BookService bookService;

    @Test
    void whenBookInformedThenItShouldBeCreated() throws NameAlreadyRegisteredException, IdNotFoundException {
        Book expectedSavedBook = BookBuilder.builder().build().toBook();
        Author author = AuthorBuilder.builder().build().toAuthor();
        String firstId = expectedSavedBook.getId();

        when(bookRepository.findByName(expectedSavedBook.getName())).thenReturn(Optional.empty());
        when(authorService.findById(author.getId())).thenReturn(author);

        Book createdBook = bookService.create(expectedSavedBook, author.getId());

        assertThat(createdBook.getId(), not(equalTo(firstId)));
        assertThat(createdBook.getName(), is(equalTo(expectedSavedBook.getName())));
        assertThat(createdBook.getAuthor(), is(equalTo(expectedSavedBook.getAuthor())));
    }

    @Test
    void whenNotRegisteredBookNameIsGivenThenThrowAnException() throws IdNotFoundException {
        Book expectedSavedBook = BookBuilder.builder().build().toBook();

        when(bookRepository.findById(expectedSavedBook.getId())).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> bookService.findById(expectedSavedBook.getId()));
    }

    @Test
    void whenValidBookIdIsGivenThenReturnABook() throws NameAlreadyRegisteredException, IdNotFoundException {
        Book expectedSavedBook = BookBuilder.builder().build().toBook();
        Author author = AuthorBuilder.builder().build().toAuthor();

        when(authorService.findById(author.getId())).thenReturn(author);

        Book createdBook = bookService.create(expectedSavedBook, author.getId());

        when(bookRepository.findById(expectedSavedBook.getId())).thenReturn(Optional.of(createdBook));

        assertThat(createdBook, is(equalTo(bookService.findById(createdBook.getId()))));
    }

    @Test
    void whenListBookIsCalledThenReturnAListOfBooks() {
        Book expectedSavedBook = BookBuilder.builder().build().toBook();

        when(bookRepository.findAll()).thenReturn(Collections.singletonList(expectedSavedBook));

        List<Book> foundListBooks = bookService.findAll();

        assertThat(foundListBooks, is(not(empty())));
        assertThat(foundListBooks.get(0), is(equalTo(expectedSavedBook)));
    }

    @Test
    void whenDeleteIsCalledWithValidIdThenABookShouldBeDeleted() throws IdNotFoundException {
        Book expectedDeletedBook = BookBuilder.builder().build().toBook();
        String idBook = "123456";

        when(bookRepository.findById(idBook)).thenReturn(Optional.of(expectedDeletedBook));
        doNothing().when(bookRepository).deleteById(idBook);

        bookService.delete(idBook);

        verify(bookRepository, times(1)).findById(idBook);
        verify(bookRepository, times(1)).deleteById(idBook);
    }

    @Test
    void whenUpdateCalledThanReturnABookUpdated() throws IdNotFoundException {
        Book expectedUpdatedBook = BookBuilder.builder().build().toBook();
        Book update = new Book("567890",
                "book2",
                new Author(),
                new ArrayList<ItensEntry>(),
                new ArrayList<ItensSell>(),
                "company2",
                "image url2",
                1995,
                0,
                0);

        when(bookRepository.findById(expectedUpdatedBook.getId())).thenReturn(Optional.of(expectedUpdatedBook));

        bookService.update(expectedUpdatedBook.getId(), update);

        assertThat(expectedUpdatedBook.getName(), is(equalTo(update.getName())));
    }
}
